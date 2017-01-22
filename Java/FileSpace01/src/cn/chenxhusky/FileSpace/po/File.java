package cn.chenxhusky.FileSpace.po;

import java.util.Date;

/**
 * 文件类（存放文件的基本信息）
 * @author husky
 *
 */
public class File {

	//属性
	private int fid;
	private String faddress;				//文件地址
	private String title;					//文件标题
	private int userid;						//标示上传文件的用户
	private String username;
	private String typename;				//初次上传时的用户定义的类型名称
	private int nice;						//点赞次数
	private int bad;						//差评次数
	private Date time;						//更新或者上传的时间
	private int tid;						//类型的id
	
	//构造方法
	/**
	 * 无参构造方法，用于读取数据库
	 */
	public File() {
		super();
	}

	/**
	 * 带参数构造，用于添加文件,时间在添加到数据库时添加
	 * @param faddress
	 * @param title
	 * @param userid
	 * @param username
	 * @param typename
	 */
	public File(String faddress, String title, int userid, String username, String typename ,int tid) {
		super();
		this.faddress = faddress;
		this.title = title;
		this.userid = userid;
		this.username = username;
		this.typename = typename;
		this.tid = tid;
		//设置默认值
		this.nice = 0;
		this.bad = 0;
	}

	//getter与setter方法
	
	public int getFid() {
		return fid;
	}
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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
	
	/**
	 * 重写toString 方法
	 */
	@Override
	public String toString() {
		return "File [fid=" + fid + ", faddress=" + faddress + ", title=" + title + ", userid=" + userid + ", username="
				+ username + ", typename=" + typename + ", nice=" + nice + ", bad=" + bad + ", time=" + time + "]";
	}
}
