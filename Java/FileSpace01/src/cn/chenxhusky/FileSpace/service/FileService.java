package cn.chenxhusky.FileSpace.service;

import java.util.List;

import cn.chenxhusky.FileSpace.po.File;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 为FileAction提供方法服务
 * @author husky
 *
 */
public interface FileService {

	/**
	 * 添加文件，需要在type_file表中创建相关的纪录（在file service层实现）
	 * @param file
	 * @param tid
	 * @return true,false
	 */
	public boolean addFile(File file,int tid);
	
	/**
	 * 删除文件，需要将tab_file表中与该文件相关的纪录一起删除掉（在file service层实现）
	 * @param fid
	 * @return true,false
	 */
	public boolean deleteFile(int fid);
	
	/**
	 * 修改文件
	 * @param file
	 * @return true,false
	 */
	public boolean modifyFile(File file);
	
	/**
	 * 分页查询相关的文件,按照时间顺序
	 * @param pager
	 * @return pager
	 */
	public Pager findFilesByPager(Pager pager);
	
	/**
	 * 查询该用户相关分类下面的文件
	 * @param uid
	 * @param tid
	 * @return List<File>
	 */
	public List<File> findFilesByUserAndType(int uid,int tid);
	
	/**
	 * 查询某个文件的详细信息
	 * @param fid
	 * @return file,null
	 */
	public File FindFileById(int fid);
}
