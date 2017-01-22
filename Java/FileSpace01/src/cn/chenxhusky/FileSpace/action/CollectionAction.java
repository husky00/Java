package cn.chenxhusky.FileSpace.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.chenxhusky.FileSpace.po.Collection;
import cn.chenxhusky.FileSpace.po.File;
import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.service.CollectionService;
import cn.chenxhusky.FileSpace.service.CollectionServiceImpl;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class CollectionAction extends ActionSupport {

	//属性
	private int id;
	private int fid;
	private int uid;
	
	//使用到的对象
	private HttpServletRequest request = ServletActionContext.getRequest();
	private User user;
	private String message;
	private CollectionService collectionservice = new CollectionServiceImpl();
	private List<File> files;
	
	private String result;
	//处理方法
	/**
	 * 添加收藏
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		user = (User) request.getSession().getAttribute("user");
		fid = Integer.parseInt(request.getParameter("fid"));
		if (collectionservice.findCollection(fid,user.getUserid()) == null) {
			Collection collection = new Collection(fid,user.getUserid());
			if (collectionservice.add(collection)) {
				String string = "{success:true,message: '添加收藏成功！'}";
				JSONObject json = JSONObject.fromObject(string);
				result = json.toString();
				return SUCCESS;
			} else {
				String string = "{success:false,message: '添加收藏失败！'}";
				JSONObject json = JSONObject.fromObject(string);
				result = json.toString();
				return SUCCESS;
			}
		} else {
			String string = "{success:true,message: '该文件已经加入收藏！'}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
			return SUCCESS;
		}
	}
	
	/**
	 * 删除收藏
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		user = (User) request.getSession().getAttribute("user");
		fid = Integer.parseInt(request.getParameter("fid"));
		if (collectionservice.delete(fid,user.getUserid())) {
			String string = "{success:true}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
			return SUCCESS;
		} else {
			String string = "{success:false}";
			JSONObject json = JSONObject.fromObject(string);
			result = json.toString();
			return SUCCESS;
		}
	}
	
	/**
	 * 返回某用户的收藏列表
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		user = (User) request.getSession().getAttribute("user");
		files = collectionservice.findCollections(user.getUserid());
		return "list";
	}
		
	//getter与setter方法
	
	public int getId() {
		return id;
	}
	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}
