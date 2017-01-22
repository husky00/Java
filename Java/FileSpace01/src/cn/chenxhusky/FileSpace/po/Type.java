package cn.chenxhusky.FileSpace.po;

/**
 * 类型类（表示文件类型的类）
 * @author husky
 *
 */
public class Type {
	
	//属性
	private int tid;			
	private int userid;			//对应的用户id
	private String tname;		//表示文件的类型
	private int parentid;		//父类别的id，用于多级类别的创建
	                                      
	//构造方法
	/**
	 * 全参构造方法，用于创建分类
	 * @param tid
	 * @param fid
	 * @param userid
	 * @param tname
	 * @param parentid
	 */
	public Type(int userid, String tname, int parentid) {
		super();
		this.userid = userid;
		this.tname = tname;
		this.parentid = parentid;
	}

	/**
	 * 无参构造方法，用于读取数据库数据
	 */
	public Type() {
		super();
	}



	//getter与setter方法
	public int getTid() {
		return tid;
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
	
	/**
	 * 重写toString 方法
	 */
	@Override
	public String toString() {
		return "Type [tid=" + tid + ", userid=" + userid + ", tname=" + tname + ", parentid="
				+ parentid + "]";
	}
}
