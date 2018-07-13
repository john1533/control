package com.fourtween.netty;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DBUtils {

	private static BoneCP connectionPool;
	
	public static Connection getConnection(){
		if(connectionPool==null){
			BoneCPConfig config = new BoneCPConfig();  
	         //数据库的JDBC URL  
	         config.setJdbcUrl("jdbc:mysql://localhost:3306/cloudcontrol");   
	         //数据库用户名  
	         config.setUsername("root");   
	         //数据库用户密码  
	         config.setPassword("123456");  
	         //数据库连接池的最小连接数  
	         config.setMinConnectionsPerPartition(5);  
	         //数据库连接池的最大连接数  
	         config.setMaxConnectionsPerPartition(10);  
	         config.setPartitionCount(1);  
	         //设置数据库连接池  
	         try {
				connectionPool = new BoneCP(config);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		  
         //从数据库连接池获取一个数据库连接  
         try {
        	 if(connectionPool!=null)
        		 return connectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // fetch a connection
        return null;
	}
	
	public static void shutdownPool(){
		synchronized (connectionPool) {
			if(connectionPool!=null){
				connectionPool.shutdown();
			}
		}
	}
}
