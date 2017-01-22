package cn.chenxhusky.FileSpace.dao;

import java.util.List;

import cn.chenxhusky.FileSpace.po.Type;

/**
 * 用于Type的数据封装
 * @author husky
 *
 */
public interface TypeDao {

	/**
	 * 添加一个分类
	 * @param type
	 * @return true,false
	 */
	public boolean addType(Type type);
	
	/**
	 * 删除一个分类 true 级联删除，false不级联删除
	 * @param tid
	 * @param flag
	 * @return true,false
	 */
	public boolean deleteType(int userid,int tid,boolean flag);
	
	/**
	 * 修改一个分类  只是修改类别的名称
	 * @param type
	 * @return true,false
	 */
	public boolean modifyType(Type type);
	
	/**
	 * 根据 用户以及父类获取相应的子类集合
	 * @param uid
	 * @param parentid
	 * @return List<Type>
	 */
	public List<Type> getTypesByUserAndParent(int uid,int parentid);
	
	/**
	 * 获取该用户下的所有的分类集合
	 * @param uid
	 * @return List<Type>
	 */
	public List<Type> getTypesByUser(int uid);
	
	/**
	 * 根据id获取该类别
	 * @param tid
	 * @return Type ,null
	 */
	public Type getTypeById(int tid);
	
	/**
	 * 根据子节点返回所有的父节点（按照层次）
	 * @param tid
	 * @return String,null
	 */
	public String getParentTypes(int tid);
}
