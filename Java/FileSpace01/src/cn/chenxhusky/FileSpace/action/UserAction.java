package cn.chenxhusky.FileSpace.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.chenxhusky.FileSpace.po.User;
import cn.chenxhusky.FileSpace.service.UserService;
import cn.chenxhusky.FileSpace.service.UserServiceImpl;
import cn.chenxhusky.FileSpace.util.MD5;
//import cn.chenxhusky.FileSpace.util.MD5;
import cn.chenxhusky.FileSpace.util.Pager;
import net.sf.json.JSONObject;


/*
 * 用于User的事件处理
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	//基本属性
	private int userid;  
	private String uname;
	private String upass;
	private String email; 
	private int isadmin;		//判断是否是管理员，0不是，1是
	private int sex;			//判断性别		1男0女
	private String phone;
	
	//用到的对象
	private UserService userservice = new UserServiceImpl();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private User user;
	private String message;
	private String currPage;
	private Pager pager;
	private String newpassword;
	
	private String result;
	
	//处理方法
	/**
	 * 用户登录处理
	 * @throws Exception
	 */
	
	public String login() throws Exception {
		
		if ((user = userservice.findUserByName(uname)) != null){
			if (user.getUpass().equals(MD5.toMD5(upass))) {
				request.getSession().setAttribute("user", user);
				return "index";
			} else {
				message = "密码错误，请重新登录！";
				return "index";
			}
			
		} else {
			message = "用户不存在！";
			return "index";
		}
	}
	
	/**
	 * 用户注销操作
	 * @throws Exception
	 */
	public String logout() throws Exception{
		
		HttpSession session;
		if ((session = request.getSession(false)) != null){
			session.removeAttribute("user");
			return "index";
		} else {
			return "index";		//返回的页面是一样的
		}
	}
	
	/**
	 * 添加用户处理
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		if (userservice.findUserByName(uname) == null) {
			user = new User(uname,MD5.toMD5("123456"),0,sex);
			user.setEmail(email);
			if (userservice.addUser(user)){
				message = "添加用户："+uname+"成功";
				return "add";
			} else {
				message = "添加用户："+uname+"失败";
				return "error";
			}
		} else {
			message = "用户名已经存在";
			return "adderror";
		}
	}
	
	
	
	/**
	 * 删除用户处理
	 * @throws Exception
	 */
	public String delete() throws Exception{
		
		userid = Integer.parseInt(request.getParameter("userid"));
		if(userservice.deleteUser(userid)){
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
	 * 分页显示用于信息列表
	 * @throws Exception
	 */
	public String lists() throws Exception {
		
		if("".equals(currPage) || currPage ==null){
			currPage = "1";
		}
		pager = new Pager();
		pager.setCurrPage(Integer.parseInt(currPage));
		userservice.findAllUserByPager(pager);
		return "list";
	}
	
	/**
	 * 用户修改密码
	 * @throws Exception
	 */
	public String modifyPassword() throws Exception {

		user = userservice.findUserById(userid);
		
		user.setUpass(MD5.toMD5(newpassword));
		if (userservice.modifyUserPassword(user)) {
			message = "修改密码成功";
			return "modifyPassword";
		} else {
			message = "修改密码失败";
			return "error";
		}
	}
	
	/**
	 * 用户修改信息,至修改用户性别以及电话
	 * @throws Exception
	 */
	public String modifyInfo() throws Exception{
		
		user = userservice.findUserById(userid);
		user.setPhone(phone);
		user.setSex(sex);
		if (userservice.modifyUserInfo(user)){
			message = "修改个人信息成功";
			request.getSession().setAttribute("user", user);
			return "modifyInfo";
		} else {
			message = "修改个人信息失败";
			return "error";
		}
	}
	
	
	
	
	//getter与setter方法
	
	
	public int getUserid() {
		return userid;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
