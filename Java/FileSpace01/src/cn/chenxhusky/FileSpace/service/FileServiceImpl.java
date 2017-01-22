package cn.chenxhusky.FileSpace.service;

import java.util.List;

import cn.chenxhusky.FileSpace.dao.FileDao;
import cn.chenxhusky.FileSpace.dao.FileDaoImpl;
//import cn.chenxhusky.FileSpace.dao.TypeFileDaoImpl;
import cn.chenxhusky.FileSpace.po.File;
//import cn.chenxhusky.FileSpace.po.TypeFile;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * 对FileService方法的实现
 * @author husky
 *
 */
public class FileServiceImpl implements FileService {

	//用到的对象
	private FileDao filedao = new FileDaoImpl();
	@Override
	public boolean addFile(File file,int tid) {
		
//		boolean result = false;
//		//添加文件
//		result = filedao.addFile(file);
//		//创建相关的纪录
//		File f = filedao.getFileByName(file.getTitle());
//		TypeFile tf =  new TypeFile(f.getFid(),tid);
//		//添加纪录到数据库
//		result = new TypeFileDaoImpl().addTypeFile(tf);
//		return result ;
		return filedao.addFile(file);
	}

	@Override
	public boolean deleteFile(int fid) {

		return filedao.deleteFile(fid);
	}

	@Override
	public boolean modifyFile(File file) {
		
		return filedao.modifyFile(file);
	}

	@Override
	public Pager findFilesByPager(Pager pager) {
		
		int totalItem = filedao.getCountsOfFiles();
		pager.setTotalItem(totalItem);
		
		pager.setTotalPage(totalItem%pager.getItemOfPage()==0?(totalItem/pager.getItemOfPage()):(totalItem/pager.getItemOfPage()+1));
		
		return filedao.getFileByPager(pager);
	}

	@Override
	public List<File> findFilesByUserAndType(int uid, int tid) {
		
		return filedao.getFilesByUserAndType(uid, tid);
	}

	@Override
	public File FindFileById(int fid) {
		
		return filedao.getFileById(fid);
	}
}
