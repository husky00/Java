package cn.chenxhusky.FileSpace.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.chenxhusky.FileSpace.po.Collection;
//import cn.chenxhusky.FileSpace.util.DBSimpleUtil;
import cn.chenxhusky.FileSpace.util.DBUtil;

public class CollectionDaoImpl implements CollectionDao {

	private static Logger logger = Logger.getLogger(CollectionDaoImpl.class);
	
	@Override
	public boolean add(Collection collection) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			System.out.println(collection.getFid()+"/"+collection.getUid());
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call addCollection(?,?)");			//9个
			proc.setInt(1, collection.getFid());
			proc.setInt(2, collection.getUid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户添加收藏的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("addCollection()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(int fid,int uid) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call deleteCollection(?,?)");
			proc.setInt(1, fid);
			proc.setInt(2, uid);
			
			int rs=proc.executeUpdate();			//
			logger.info("用户删除收藏的操作！");		//这里的删除不是真的删除，只是将用户与文件的关联解除
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("deleteCollection()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Collection getCollectionById(int id) {
		Collection collection = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findCollectionById(?)");
			proc.setInt(1, id);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过收藏id获取该收藏信息的操作！");
        	while(rs.next()){
        		collection = new Collection();
        		collection.setId(rs.getInt("id"));
        		collection.setFid(rs.getInt("fid"));
        		collection.setUid(rs.getInt("uid"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getCollectionById()方法出错！");
				e.printStackTrace();
			}
		}
		return collection;
	}

	@Override
	public List<Collection> getCollections(int uid) {
		List<Collection> collections = new ArrayList<Collection>();
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findCollections(?)");
			proc.setInt(1, uid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("获取该用户所有的收藏！");
        	while(rs.next()){
        		Collection collection = new Collection();
        		collection = new Collection();
        		collection.setId(rs.getInt("id"));
        		collection.setFid(rs.getInt("fid"));
        		collection.setUid(rs.getInt("uid"));
        		collections.add(collection);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getCollections()方法出错！");
				e.printStackTrace();
			}
		}
		return collections;
	}

	@Override
	public Collection getCollection(int fid, int uid) {
		Collection collection = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			System.out.println(fid+"/"+uid+"get");
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findCollection(?,?)");
			proc.setInt(1, fid);
			proc.setInt(2, uid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过用户以及文件查看该用户是够已经收藏了该文件的操作！");
        	while(rs.next()){
        		collection = new Collection();
        		collection.setId(rs.getInt("id"));
        		collection.setFid(rs.getInt("fid"));
        		collection.setUid(rs.getInt("uid"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getCollection()方法出错！");
				e.printStackTrace();
			}
		}
//		System.out.println(collection+"dao");
		return collection;
	}

}
