package cn.chenxhusky.FileSpace.po;

/**
 * 收藏类
 * @author husky
 */
public class Collection {

	//属性
	private int id;
	private int fid;
	private int uid;
	
	public Collection() {
		super();
	}
	public Collection(int fid, int uid) {
		super();
		this.fid = fid;
		this.uid = uid;
	}
	
	public int getId() {
		return id;
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
