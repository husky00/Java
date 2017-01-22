package cn.chenxhusky.FileSpace.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.chenxhusky.FileSpace.po.Type;
import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.service.TypeService;
import cn.chenxhusky.FileSpace.service.TypeServiceImpl;

/*
 * 用于Type的事件处理
 */
@SuppressWarnings("serial")
public class TypeAction extends ActionSupport {

	//基本属性
	private int tid;			
	private int userid;			//对应的用户id
	private String tname;		//表示文件的类型
	private int parentid;		//父类别的id，用于多级类别的创建
	
	//使用的对象
	private List<Type> types;
	private TypeService typeservice = new TypeServiceImpl();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private User user = (User) request.getSession().getAttribute("user");
	private boolean flag;
	private String message;
//	private static Logger logger = Logger.getLogger(FileAction.class);
	//处理方法
	/**
	 * 添加分类，需要指定分类的名称，对应的用户id，父类
	 * @throws Exception
	 */
	public String add() throws Exception {
		/**
		 * 不判断是否有同名的分类
		 */
		Type type = new Type(user.getUserid(),tname,parentid);
		if (typeservice.addType(type)) {
			message = "添加分类成功";
			return "add";
		} else {
			message = "添加分类失败";
			return "error";
		}
	}
	
	/**
	 * 删除某用户下面的分类，分成级联删除文件，以及不删除文件方式
	 * 级联删除文件不是真的删除文件，而是将用户与文件之间的关系去掉（type.file表）
	 * 不删除文件方式是将原有分类的文件分类关系改成默认的分类
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
//		System.out.println("action+delete"+" "+ flag);
		
		if (typeservice.deleteType(user.getUserid(), tid, flag)) {
			message = "删除分类成功";
			return "delete";
		} else {
			message = "删除分类成功";
			return "error";
		}
	}
	
	/**
	 * 未使用
	 * 修改分类名称
	 * @throws Exception
	 */
	public String modify() throws Exception {
		
		Type type = typeservice.findTypeById(tid);
		type.setTname(tname);
		if (typeservice.modifyType(type)) {
			return "modify";
		} else {
			return "error";
		}
	}
	
	
	/**
	 * 获取该用户下面所有的分类
	 * @throws Exception
	 */
	public String lists() throws Exception {
		
		types = typeservice.findTypesByUser(user.getUserid());
		return "lists";
	}
	
	/**
	 * 用于分类管理页面
	 * @throws Exception
	 */
	public String managelists() throws Exception {
		
		types = typeservice.findTypesByUser(user.getUserid());
		return "managelists";
	}
	
	//getter与setter方法
	
	public int getTid() {
		return tid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
}
