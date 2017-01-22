package cn.chenxhusky.FileSpace.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.chenxhusky.FileSpace.po.User;
//import cn.chenxhusky.FileSpace.util.DBSimpleUtil;
import cn.chenxhusky.FileSpace.util.DBUtil;
import cn.chenxhusky.FileSpace.util.Pager;

/**
 * UserDao方法实现
 * @author husky
 *
 */
public class UserDaoImpl implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Override
	public User getUserById(int userid) {
		User user = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findUserById(?)");
			proc.setInt(1, userid);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过用户id查找该用户的操作！");
        	while(rs.next()){
        		user = new User();
        		user.setUserid(rs.getInt("userid"));
        		user.setUname(rs.getString("uname"));
        		user.setUpass(rs.getString("upass"));
        		user.setEmail(rs.getString("email"));
        		user.setIsadmin(rs.getInt("isadmin"));
        		user.setSex(rs.getInt("sex"));
        		user.setPhone(rs.getString("phone"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getUserById()方法出错！");
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User getUserByName(String uname) {
		User user = null;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findUserByName(?)");
			proc.setString(1, uname);
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行通过用户name查找该用户的操作！");
        	while(rs.next()){
        		user = new User();
        		user.setUserid(rs.getInt("userid"));
        		user.setUname(rs.getString("uname"));
        		user.setUpass(rs.getString("upass"));
        		user.setEmail(rs.getString("email"));
        		user.setIsadmin(rs.getInt("isadmin"));
        		user.setSex(rs.getInt("sex"));
        		user.setPhone(rs.getString("phone"));
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getUserByName()方法出错！");
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call addUser(?,?,?,?,?)");
			proc.setString(1, user.getUname());
			proc.setString(2, user.getUpass());
			proc.setInt(3, user.getIsadmin());
			proc.setInt(4, user.getSex());
			proc.setString(5, user.getEmail());
			
			int rs=proc.executeUpdate();			//
			logger.info("管理员添加用户的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("addUser()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteUser(int userid) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call deleteUserById(?)");
			proc.setInt(1, userid);
			
			int rs=proc.executeUpdate();			//
			logger.info("管理员删除用户的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("deleteUserById()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean modifyUserInfo(User user) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call modifyUserInfo(?,?,?,?)");
			proc.setString(1, user.getEmail());
			proc.setInt(2, user.getSex());
			proc.setString(3, user.getPhone());
			proc.setInt(4, user.getUserid());
			int rs=proc.executeUpdate();			//
			logger.info("用户修改基本信息的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("modifyUserInfo()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public boolean modifyUserPassword(User user) {
		boolean result = false;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call modifyPassword(?,?)");
			proc.setString(1, user.getUpass());
			proc.setInt(2, user.getUserid());
			
			int rs=proc.executeUpdate();			//
			logger.info("用户修改密码的操作！");
			if(rs>0)result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("modifyUserPassword()方法出错！");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int getCountsOfUsers() {
		int count = 0;
		Connection conn=null;
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findTheCountOfUsers()");
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行查询用户数量的操作！");
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
				logger.error("getCountOfUsers()方法出错！");
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public Pager getUserByPager(Pager pager) {
		Connection conn=null;
		int startItem = (pager.getCurrPage()-1)*pager.getItemOfPage();
		CallableStatement proc = null;
		try{
//			conn=DBSimpleUtil.openConnection();
			conn = DBUtil.openConnection();
			proc = conn.prepareCall("call findUserByPager(?,?)");
			proc.setInt(1, startItem);
			proc.setInt(2, pager.getItemOfPage());
			
			ResultSet rs=proc.executeQuery();			//
			logger.info("执行分页获取用户数据的操作！");
        	while(rs.next()){
        		User user = new User();
        		user.setUserid(rs.getInt("userid"));
        		user.setUname(rs.getString("uname"));
        		user.setUpass(rs.getString("upass"));
        		user.setEmail(rs.getString("email"));
        		user.setIsadmin(rs.getInt("isadmin"));
        		user.setSex(rs.getInt("sex"));
        		user.setPhone(rs.getString("phone"));
        		pager.getItems().add(user);
        	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
//				DBSimpleUtil.closeConnection();
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				logger.error("getUserByPager()方法出错！");
				e.printStackTrace();
			}
		}
		return pager;
	}

	
	public static void main(String[] args) {
//		UserDao userdao = new UserDaoImpl();
//		System.out.println(userdao.getCountsOfUsers());
//		System.out.println(userdao.getUserById(1).toString());
//		System.out.println(userdao.getUserByName("tom").toString());
	}
}
