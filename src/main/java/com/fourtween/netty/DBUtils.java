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
	         //���ݿ��JDBC URL  
	         config.setJdbcUrl("jdbc:mysql://localhost:3306/cloudcontrol");   
	         //���ݿ��û���  
	         config.setUsername("root");   
	         //���ݿ��û�����  
	         config.setPassword("123456");  
	         //���ݿ����ӳص���С������  
	         config.setMinConnectionsPerPartition(5);  
	         //���ݿ����ӳص����������  
	         config.setMaxConnectionsPerPartition(10);  
	         config.setPartitionCount(1);  
	         //�������ݿ����ӳ�  
	         try {
				connectionPool = new BoneCP(config);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		  
         //�����ݿ����ӳػ�ȡһ�����ݿ�����  
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
