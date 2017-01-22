package cn.chenxhusky.FileSpace.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.chenxhusky.FileSpace.po.File;
import cn.chenxhusky.FileSpace.po.Type;
import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.service.FileService;
import cn.chenxhusky.FileSpace.service.FileServiceImpl;
import cn.chenxhusky.FileSpace.service.TypeService;
import cn.chenxhusky.FileSpace.service.TypeServiceImpl;
import cn.chenxhusky.FileSpace.util.Pager;
import net.sf.json.JSONObject;

/*
 * 用于File的事件处理
 */
@SuppressWarnings("serial")
public class FileAction extends ActionSupport {

	//基本属性
	private int fid;
	private String faddress;				//文件地址
	private String title;					//文件标题
	private int userid;						//标示上传文件的用户
	private String username;
	private String typename;				//初次上传时的用户定义的类型名称
	private int nice;						//点赞次数
	private int bad;						//差评次数
	private Date time;						//更新或者上传的时间
	
	//使用到的对象
	private FileService fileservice = new FileServiceImpl();
	private TypeService typeservice = new TypeServiceImpl();
	private Pager pager;
	private String currPage;
	private User user;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private static Logger logger = Logger.getLogger(FileAction.class);
	
	private int tid;
	private java.io.File file;
	private File download;
	private String fileFileName;			//上传文件的文件名称
	private String fileContentType;		//上传的文件类型MIME
	private List<File> files;
	private Map<Integer,String> parenttypes;
	private Set<Integer> tids;
	private List<Type> types;
	private String message;
	
	
	private String result;
	
	private InputStream fileDown;
	//处理方法
	
	
	/**
	 *	添加点赞次数
	 * @return 
	 * @return
	 * @throws Exception
	 */
	public String praise() throws Exception{
		
		//获取当前session里面的用户对象
		user = (User) request.getSession().getAttribute("user");
		fid = Integer.parseInt(request.getParameter("niceId"));//获得ajax传过来的data（niceId）;
		File file = fileservice.FindFileById(fid);
		int niceNum = file.getNice()+1;
		file.setNice(niceNum);
		if(fileservice.modifyFile(file)){
			String string = "{success:true,niceNum:"+niceNum+"}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
//			result.put("success", "true");
//			result.put("niceNum", String.valueOf(niceNum));
			return SUCCESS;
		}else{
			String string = "{success:false}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
//			result.put("success", "false");
			return SUCCESS;
		}
	}
	
	
	/**
	 *	添加差评次数
	 * @return 
	 * @return
	 * @throws Exception
	 */
	public String bad() throws Exception{
		
		//获取当前session里面的用户对象
//		HttpServletRequest request =ServletActionContext.getRequest();
		fid = Integer.parseInt(request.getParameter("badId"));//获得ajax传过来的data（niceId）;
		File file = fileservice.FindFileById(fid);
		int badNum = file.getBad()+1;
		file.setBad(badNum);;
		if(fileservice.modifyFile(file)){
			String string = "{success:true,badNum:"+badNum+"}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
			return SUCCESS;
		}else{
			String string = "{success:false}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
			return SUCCESS;
		}
	}
	
	/**
	 * 文件列表（不分用户） 
	 * @throws Exception
	 */
	public String lists() throws Exception {
		
		if("".equals(currPage) || currPage ==null){
			currPage = "1";
		}
		pager = new Pager();
		pager.setCurrPage(Integer.parseInt(currPage));
		pager = fileservice.findFilesByPager(pager);
//		System.out.println(pager.getCurrPage()+"TotalItem "+pager.getTotalItem() + "ItemOfPage: " +pager.getItemOfPage() + "TotalPage: "+ pager.getTotalPage());
		return "lists";
	}
	
	/**
	 * 上传文件
	 * @throws Exception
	 */
	public String upload() throws Exception {
		
		//获取当前用户，以使用其userid,uname
		user = (User) request.getSession().getAttribute("user");
		//获取typename
		String typename = typeservice.findTypeById(tid).getTname();
		//获取存储地址
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		String address = root + "/" + fileFileName;
		
		try {
				uploadFile(root,fileFileName);		//将文件写入到数据库
				//将相关数据添加到数据库
				File file = new File(address,fileFileName,user.getUserid(),user.getUname(),typename,tid);							//自定义的file
				if (fileservice.addFile(file, tid)) {
					message = "文件上传成功！";
					logger.info("文件上传成功！");
					return "upload";
				} else {
					message = "文件上传失败！";
					logger.info("文件上传失败！");
					return "upload";
				}
			
		} catch(IOException e) {
			logger.error("文件写入出错！");
			e.printStackTrace();
			return "error";
		}
		
	}
	
	/**
	 * 下载文件
	 * @throws Exception
	 */
	public String download() throws Exception {
		
		download = fileservice.FindFileById(fid);
		fileFileName = download.getTitle();
//		System.out.println(fileFileName);
		fileContentType = "application/msword";
		fileDown = ServletActionContext.getServletContext().getResourceAsStream("/upload/"+fileFileName);
		return "download";
	}
	
	
	public String open() throws Exception {
		
		download = fileservice.FindFileById(fid);
		fileFileName = download.getTitle();
		System.out.println(fileFileName);
		fileContentType = "application/msword";
		fileDown = ServletActionContext.getServletContext().getResourceAsStream("/upload/"+fileFileName);
		return "open";
	}
	/**
	 * 删除文件（只能是上传者进行删除）
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		if (fileservice.deleteFile(fid)){
			return "delete";
		} else {
			return "error";
		}
	}
	
	/**
	 * 根据分类以及用户返回该用户的文件userid+tid
	 * @throws Exception
	 */
	public String mylists() throws Exception {
		
		//获取当前session里面的用户对象
		user = (User) request.getSession().getAttribute("user");
		//获取文件集,传入的tid就是父类的type
		tids = typeservice.findParentTypes(tid).keySet();
		parenttypes = typeservice.findParentTypes(tid);
		
		files = fileservice.findFilesByUserAndType(user.getUserid(), tid);
		types = typeservice.findTypesByUserAndParent(user.getUserid(), tid);
		return "mylists";
	}
	
	/**
	 * 将文件读取到服务器的操作
	 * @return true,false
	 * @throws IOException 
	 */
	private boolean uploadFile(String root, String fileFileName) throws IOException {
		
		InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream(new java.io.File(root, fileFileName));
        byte[] buffer = new byte[500];
        int length = 0;
        
        while(-1 != (length = is.read(buffer)))
        {
            os.write(buffer,0,length);
        }
        
        os.close();
        is.close();
		return true;
	}
	
	//getter与setter方法
	
	
	public int getFid() {
		return fid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InputStream getFileDown() {
		return fileDown;
	}

	public void setFileDown(InputStream fileDown) {
		this.fileDown = fileDown;
	}

	public File getDownload() {
		return download;
	}

	public void setDownload(File download) {
		this.download = download;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public Map<Integer, String> getParenttypes() {
		return parenttypes;
	}

	public void setParenttypes(Map<Integer, String> parenttypes) {
		this.parenttypes = parenttypes;
	}

	public Set<Integer> getTids() {
		return tids;
	}

	public void setTids(Set<Integer> tids) {
		this.tids = tids;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public java.io.File getFile() {
		return file;
	}

	public void setFile(java.io.File file) {
		this.file = file;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public FileService getFileservice() {
		return fileservice;
	}

	public void setFileservice(FileService fileservice) {
		this.fileservice = fileservice;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getNice() {
		return nice;
	}
	public void setNice(int nice) {
		this.nice = nice;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
