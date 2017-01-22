package cn.chenxhusky.FileSpace.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.chenxhusky.FileSpace.po.File;
//import cn.chenxhusky.FileSpace.util.DBSimpleUtil;
import cn.chenxhusky.FileSpace.util.DBUtil;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * FileDao方法实现
 * @author husky
 *
 */
public class FileDaoImpl implements FileDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Override  //OK
	public boolean addFile(File file) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call addFile(?,?,?,?,?,?,?,?,?)");			//9个
			proc.setString(1, file.getFaddress());
			proc.setString(2, file.getTitle());
			proc.setInt(3, file.getUserid());
			proc.setString(4, file.getUsername());
			proc.setString(5, file.getTypename());
			proc.setInt(6, file.getNice());
			proc.setInt(7, file.getBad());
			proc.setDate(8, new java.sql.Date(new java.util.Date().getTime()));		//前一个getTime()是file的属性方法，后面一个是将Date类型转化成long类型
			proc.setInt(9, file.getTid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户添加文件的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("addFile()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override  //OK
	public boolean deleteFile(int fid) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call deleteFile(?)");
			proc.setInt(1, fid);
			
			int rs=proc.executeUpdate();			//
			logger.info("用户删除文件的操作！");		//这里的删除不是真的删除，只是将用户与文件的关联解除
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("deleteFile()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override   //OK
	public boolean modifyFile(File file) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call modifyFile(?,?,?)");
			proc.setInt(1, file.getNice());
			proc.setInt(2, file.getBad());
			proc.setInt(3, file.getFid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户修改文件的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("modifyFile()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override	//OK
	public Pager getFileByPager(Pager pager) {
		Connection conn=null;
		int startItem = (pager.getCurrPage()-1)*pager.getItemOfPage();
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findFileByPager(?,?)");
			proc.setInt(1, startItem);
			proc.setInt(2, pager.getItemOfPage());
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行分页获取文件的操作！");
        	while(rs.next()){
        		File file = new File();
        		file.setFid(rs.getInt("fid"));
        		file.setFaddress(rs.getString("faddress"));
        		file.setTitle(rs.getString("title"));
        		file.setUserid(rs.getInt("userid"));
        		file.setUsername(rs.getString("username"));
        		file.setTypename(rs.getString("typename"));
        		file.setNice(rs.getInt("nice"));
        		file.setBad(rs.getInt("bad"));
        		file.setTime((java.util.Date)rs.getDate("time"));
        		file.setTid(rs.getInt("tid"));
//        		System.out.println((java.util.Date)rs.getDate("time"));
        		pager.getItems().add(file);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getFileByPager()方法出错！");
				e.printStackTrace();
			}
		}
		return pager;
	}

	@Override		//OK
	public List<File> getFilesByUserAndType(int uid, int tid) {
		List<File> files = new ArrayList<File>();
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findFilesByUserAndType(?,?)");
			proc.setInt(1, uid);
			proc.setInt(2, tid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行分页获取文件的操作！");
        	while(rs.next()){
        		File file = new File();
        		file.setFid(rs.getInt("fid"));
        		file.setFaddress(rs.getString("faddress"));
        		file.setTitle(rs.getString("title"));
        		file.setUserid(rs.getInt("userid"));
        		file.setUsername(rs.getString("username"));
        		file.setTypename(rs.getString("typename"));
        		file.setNice(rs.getInt("nice"));
        		file.setBad(rs.getInt("bad"));
        		file.setTime((java.util.Date)rs.getTime("time"));
        		file.setTid(rs.getInt("tid"));
        		files.add(file);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getFileByPager()方法出错！");
				e.printStackTrace();
			}
		}
		return files;
	}

	@Override		//OK
	public File getFileById(int fid) {
		File file = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findFileById(?)");
			proc.setInt(1, fid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过文件id获取该文件信息的操作！");
        	while(rs.next()){
        		file = new File();
        		file.setFid(rs.getInt("fid"));
        		file.setFaddress(rs.getString("faddress"));
        		file.setTitle(rs.getString("title"));
        		file.setUserid(rs.getInt("userid"));
        		file.setUsername(rs.getString("username"));
        		file.setTypename(rs.getString("typename"));
        		file.setNice(rs.getInt("nice"));
        		file.setBad(rs.getInt("bad"));
        		file.setTime((java.util.Date)rs.getTime("time"));
        		file.setTid(rs.getInt("tid"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getFileById()方法出错！");
				e.printStackTrace();
			}
		}
		return file;
	}

	@Override		//OK
	public File getFileByName(String title) {
		File file = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findFileByTitle(?)");
			proc.setString(1, title);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过文件Name获取该文件信息的操作！");
        	while(rs.next()){
        		file = new File();
        		file.setFid(rs.getInt("fid"));
        		file.setFaddress(rs.getString("faddress"));
        		file.setTitle(rs.getString("title"));
        		file.setUserid(rs.getInt("userid"));
        		file.setUsername(rs.getString("username"));
        		file.setTypename(rs.getString("typename"));
        		file.setNice(rs.getInt("nice"));
        		file.setBad(rs.getInt("bad"));
        		file.setTime((java.util.Date)rs.getTime("time"));
        		file.setTid(rs.getInt("tid"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getFileByName()方法出错！");
				e.printStackTrace();
			}
		}
		return file;
	}

	@Override		//OK
	public int getCountsOfFiles() {
		int count = 0;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findTheCountOfFiles()");
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行查询文件数量的操作！");
        	while(rs.next()){
        		count = rs.getInt("count(*)");
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getCountOfFiles()方法出错！");
				e.printStackTrace();
			}
		}
		return count;
	}

}
