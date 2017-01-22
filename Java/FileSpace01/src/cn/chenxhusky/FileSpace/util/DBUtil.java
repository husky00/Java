package cn.chenxhusky.FileSpace.util;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Calendar;
//import java.util.Random;

import org.apache.log4j.Logger;

 

/**
 * JDBC访问数据库基础
 * @author JOHN1
 *
 */
public class DBUtil {

	private static Logger logger = Logger.getLogger(DBUtil.class);
	
     public static Connection openConnection(){
    	 Connection conn=null;
    	 try{
    		 logger.info("打开一个数据库连接！");
    		 conn=JndiBean.getConnection();
    	 }catch(Exception e){
    		 logger.error("打开数据库连接时出错！"+e.getMessage());
    	 }
    	 return conn;
     }
     
     public static void closeConnection(Connection conn){
    	 try{
    		 if(conn!=null&&!conn.isClosed()){
    			 conn.close();
    			 logger.info("关闭一个数据库连接！");
    		 }
    	 }catch(Exception e){
    		 logger.error("关闭一个数据库连接出现："+e.getMessage());
    		 
    	 }
     }
}
