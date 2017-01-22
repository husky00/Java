package cn.chenxhusky.FileSpace.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;



/**
 * 返回数据库连接
 * @author husky
 * 未使用
 */
public class DBSimpleUtil {

	private static Logger logger = Logger.getLogger(DBSimpleUtil.class);
	
	public static String URL="jdbc:mysql://115.159.70.195:3306/FileSpace?characterEncoding=UTF-8";
	public static String USER="root";
	public static String PASS="123456";
	public static Connection conn=null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection openConnection(){
		try{
			if(conn==null||conn.isClosed())
				logger.info("打开数据库！");
				conn=DriverManager.getConnection(URL, USER, PASS);
			
		}catch(Exception e){
			logger.error("打开数据库出错！");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(){
		try{
			if(conn!=null&&!conn.isClosed()){
				logger.info("关闭数据库！");
				conn.close();
			}
		}catch(Exception e){
			logger.error("关闭数据库出错！");
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DBSimpleUtil.openConnection();
		DBSimpleUtil.closeConnection();
	}
}
