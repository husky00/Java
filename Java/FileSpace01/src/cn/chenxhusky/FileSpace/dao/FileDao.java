package cn.chenxhusky.FileSpace.dao;

import java.util.List;

import cn.chenxhusky.FileSpace.po.File;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 用于File信息的封装
 * @author husky
 *
 */
public interface FileDao {

	/**
	 * 添加文件
	 * @param file
	 * @return true,false
	 */
	public boolean addFile(File file);
	
	/**
	 * 删除文件
	 * @param fid
	 * @return true,false
	 */
	public boolean deleteFile(int fid);
	
	/**
	 * 修改文件		只是用来修改文件的nice与bad
	 * @param file
	 * @return true,false
	 */
	public boolean modifyFile(File file);
	
	/**
	 * 分页查询文件记录，用于首页
	 * @param pager
	 * @return
	 */
	public Pager getFileByPager(Pager pager);
	
	/**
	 * 查询相应用户下面相关分类的文件列表
	 * @param uid
	 * @param tid
	 * @return
	 */
	public List<File> getFilesByUserAndType(int uid,int tid);
	
	/**
	 * 根据文件id获取文件的详细信息
	 * @param fid
	 * @return File,null
	 */
	public File getFileById(int fid);
	
	/**
	 * 根据文件名称得到该文件信息
	 * @param title
	 * @return File,null
	 */
	public File getFileByName(String title);
	
	/**
	 * 获取文件的总数量
	 * @return count
	 */
	public int getCountsOfFiles();
}
