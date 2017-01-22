package cn.chenxhusky.FileSpace.po;

/**
 * 用户类（标识用户的基本信息，其中用is administrator标示是否是管理员 1表示这是管理员）
 * @author husky
 *
 */
public class User {

	
	//基本属性
	private int userid;  
	private String uname;
	private String upass;
	private String email;
	private int isadmin;		//判断是否是管理员，0不是，1是
	private int sex;			//判断性别		1男0女
	private String phone;
	
	//构造方法
	
	/**
	 * 全参构造函数，用于修改个人信息
	 * @param userid
	 * @param uname
	 * @param upass
	 * @param email
	 * @param isadmin
	 * @param sex
	 * @param phone
	 */
	public User(int userid, String uname, String upass, String email, int isadmin, int sex, String phone) {
		super();
		this.userid = userid;
		this.uname = uname;
		this.upass = upass;
		this.email = email;
		this.isadmin = isadmin;
		this.sex = sex;
		this.phone = phone;
	}
	/**
	 * 四参构造函数，用于管理员添加用户
	 * @param uname
	 * @param upass
	 * @param isadmin
	 * @param sex
	 */
	public User(String uname, String upass, int isadmin, int sex) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.isadmin = isadmin;
		this.sex = sex;
	}
	/**
	 * 无参构造函数，用于读取数据库
	 */
	public User() {
		super();
	}
	//getter与setter方法
	public int getUserid() {
		return userid;
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
	
	/**
	 * toString重写
	 */
	@Override
	public String toString() {
		return "User [userid=" + userid + ", uname=" + uname + ", upass=" + upass + ", email=" + email + ", isadmin="
				+ isadmin + ", sex=" + sex + ", phone=" + phone + "]";
	}
}
