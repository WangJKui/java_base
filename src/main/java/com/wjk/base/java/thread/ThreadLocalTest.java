package com.wjk.base.java.thread;

import java.sql.Connection;
import java.sql.DriverManager;

public class ThreadLocalTest {
	//数据库配置
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/pay-routing";
	private static final String username="root";
	private static final String password="huojianguo";

	private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();
	/**
	 * 连接Connection
	 * @return
	 */
	public static Connection getConnection(){

		Connection conn = connContainer.get();

		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);   
				connContainer.set(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;								

	}
	/**
	 * 关闭Connection，清除集合中的Connection
	 */
	public static void closeConnection(){
		Connection conn=connContainer.get();
	    try {
	        if(conn!=null){
	            conn.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	        connContainer.remove();
	    }
	}

}
