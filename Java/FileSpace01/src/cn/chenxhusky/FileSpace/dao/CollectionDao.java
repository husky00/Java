package cn.chenxhusky.FileSpace.dao;

import java.util.List;

import cn.chenxhusky.FileSpace.po.Collection;

public interface CollectionDao {

	/**
	 * 添加收藏
	 * @param collection
	 * @return true , false
	 */
	public boolean add(Collection collection);
	
	/**
	 * 根据id删除收藏
	 * @param fid
	 * @param uid
	 * @return true ,false 
	 */
	public boolean delete(int fid,int uid);
	
	/**
	 * 根据id获取该收藏的信息
	 * @param id
	 * @return true,false
	 */
	public Collection getCollectionById(int id);
	
	/**
	 * 获取某个用户的所有收藏
	 * @param uid
	 * @return List<Collection>
	 */
	public List<Collection> getCollections(int uid);
	
	/**
	 * 通过用户以及文件查看该用户是够已经收藏了该文件
	 * @param fid
	 * @param uid
	 * @return null,collection
	 */
	public Collection getCollection(int fid,int uid);
}
