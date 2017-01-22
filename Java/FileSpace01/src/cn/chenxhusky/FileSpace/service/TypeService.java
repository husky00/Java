package cn.chenxhusky.FileSpace.service;

import java.util.List;
import java.util.Map;

import cn.chenxhusky.FileSpace.po.Type;

/**
 * 为TypeAction提供方法服务
 * @author husky
 */
public interface TypeService {

	/**
	 * 添加分类
	 * @param type
	 * @return true,false
	 */
	public boolean addType(Type type);
	
	/**
	 * 删除分类，直接调用dao层就行，两种情况在dao层分好了已经
	 * @param userid
	 * @param tid
	 * @param flag
	 * @return true,false
	 */
	public boolean deleteType(int userid,int tid,boolean flag);
	
	/**
	 * 修改分类，只能修改名称，嘿嘿
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
	public List<Type> findTypesByUserAndParent(int uid,int parentid);
	
	/**
	 * 获取该用户下的所有的分类集合
	 * @param uid
	 * @return List<Type>
	 */
	public List<Type> findTypesByUser(int uid);
	
	/**
	 * 根据id获取该类别对象
	 * @param tid
	 * @return Type,null
	 */
	public Type findTypeById(int tid);
	
	/**
	 * 获取某子节点的所有父节点
	 * @param tid
	 * @return map
	 */
	public Map<Integer,String> findParentTypes(int tid);
}
