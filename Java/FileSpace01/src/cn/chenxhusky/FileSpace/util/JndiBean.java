package cn.chenxhusky.FileSpace.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 通过JNDI查询数据源，返回数据库连接。
 * 
 * @author john
 * 
 */
public class JndiBean {
	
	private static Logger logger = Logger.getLogger(JndiBean.class);
	/**
	 * 
	 * @return  
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static Connection getConnection() throws DBPoolException, NamingException, SQLException {
		Connection conn = null;
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("UserDatabase");
		if (ds != null) {
			conn = ds.getConnection();
		} else {
			logger.error("Can't find JNDI data source!");
			throw new DBPoolException("Can't find JNDI data source!");
		}
		if (conn == null) {
			logger.error("Can't establish connection!");
			throw new DBPoolException("Can't establish connection!");
		}
		return conn;
	}
	
	/*public static void main(String args[]) throws DBPoolException, NamingException, SQLException{
		Connection conn=JndiBean.getConnection();
		System.out.println("OK");
	}*/
}
