package cn.chenxhusky.FileSpace.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.chenxhusky.FileSpace.po.Type;
//import cn.chenxhusky.FileSpace.util.DBSimpleUtil;
import cn.chenxhusky.FileSpace.util.DBUtil;

/**
 * TypeDao方法实现
 * @author husky
 *
 */
public class TypeDaoImpl implements TypeDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	//需要添加tid,userid,tname,parentid
	@Override
	public boolean addType(Type type) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call addType(?,?,?)");
			proc.setInt(1, type.getUserid());
			proc.setString(2, type.getTname());
			proc.setInt(3, type.getParentid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户添加新的分类的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("addType()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	//根据tid删除该类别
	@Override
	public boolean deleteType(int userid,int tid,boolean flag) {
		boolean result = false;
		
		System.out.println("dao");
		
		Connection conn=null;
		CallableStatement proc = null;
		try {
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			if (flag) {
				proc = conn.prepareCall("call deleteTypeWithFile(?,?)");	//级联删除文件（删除关联）
			} else {
				proc = conn.prepareCall("call deleteType(?,?)");			//不删除文件（不删除关联，只是将类别改成默认）
			}
			proc.setInt(1, userid);
			proc.setInt(2, tid);
			int rs=proc.executeUpdate();			//
			logger.info("用户删除分类的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("deleteType()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	//修改该类别
	@Override
	public boolean modifyType(Type type) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call modifyType(?,?)");
			proc.setString(1, type.getTname());
			proc.setInt(2, type.getTid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户修改分类的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("modifyType()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	//根据uid以及parentid获取相应的类别列表
	@Override
	public List<Type> getTypesByUserAndParent(int uid, int parentid) {
		List<Type> types = new ArrayList<Type>();
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findTypesByUserAndParent(?,?)");
			proc.setInt(1, uid);
			proc.setInt(2, parentid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过用户id以及父类查找子类的操作！");
        	while(rs.next()){
        		Type type = new Type();
        		type.setTid(rs.getInt("tid"));
        		type.setUserid(rs.getInt("userid"));
        		type.setTname(rs.getString("tname"));
        		type.setParentid(rs.getInt("parentid"));
        		types.add(type);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getTypeByUserAndParent()方法出错！");
				e.printStackTrace();
			}
		}
		return types;
	}

	//通过用户获取该用户下面所有的类别
	@Override
	public List<Type> getTypesByUser(int uid) {
		List<Type> types = new ArrayList<Type>();
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findTypesByUserId(?)");
			proc.setInt(1, uid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过用户id分类的操作！");
        	while(rs.next()){
        		Type type = new Type();
        		type.setTid(rs.getInt("tid"));
        		type.setUserid(rs.getInt("userid"));
        		type.setTname(rs.getString("tname"));
        		type.setParentid(rs.getInt("parentid"));
        		types.add(type);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getTypeByUser()方法出错！");
				e.printStackTrace();
			}
		}
		return types;
	}

	@Override
	public Type getTypeById(int tid) {
		Type type = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findTypeById(?)");
			proc.setInt(1, tid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过id获取分类的操作！");
        	while(rs.next()){
        		type = new Type();
        		type.setTid(rs.getInt("tid"));
        		type.setUserid(rs.getInt("userid"));
        		type.setTname(rs.getString("tname"));
        		type.setParentid(rs.getInt("parentid"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getTypeById()方法出错！");
				e.printStackTrace();
			}
		}
		return type;
	}

	@Override
	public String getParentTypes(int tid) {
		String str = "";
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findParents(?)");
			proc.setInt(1, tid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("用户获取某类别节点的所有的父节点的操作！");
			
			while(rs.next()){
				str = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getParentTypes()方法出错！");
				e.printStackTrace();
			}
		}
		return str;
	}

//	public static void main(String[] args) {
//		TypeDao ty = new TypeDaoImpl();
//		System.out.println(ty.getParentTypes(9));
//	}
}
