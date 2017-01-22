package cn.chenxhusky.FileSpace.service;

import java.util.List;

import cn.chenxhusky.FileSpace.po.Collection;
import cn.chenxhusky.FileSpace.po.File;

public interface CollectionService {

	/**
	 * 添加收藏
	 * @param collection
	 * @return true,false
	 */
	public boolean add(Collection collection);
	
	/**
	 * 删除收藏
	 * @param fid
	 * @param uid
	 * @return true ,false 
	 */
	public boolean delete(int fid,int uid);
	
	/**
	 * 获取某用户所有的收藏
	 * @param uid
	 * @return List<File>
	 */
	public List<File> findCollections(int uid);
	
	/**
	 * 通过用户以及文件查看该用户是够已经收藏了该文件
	 * @param fid
	 * @param uid
	 * @return null,collection
	 */
	public Collection findCollection(int fid, int uid);
}
