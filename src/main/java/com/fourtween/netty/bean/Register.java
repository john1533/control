package com.fourtween.netty.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fourtween.netty.DBUtils;

public class Register extends BaseBean{
	private static Logger logger = Logger.getLogger(Register.class);
	private int width;
	private int height;
	private String model;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public void doExecute(){
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("insert into t_phone(imei,model,width,heigh,enable,createtime) values(?,?,?,?,?,?)");
			pstm.setString(1, imei);
			pstm.setString(2, model);
			pstm.setInt(3, width);
			pstm.setInt(4, height);
			pstm.setInt(5, 1);
			pstm.setLong(6, System.currentTimeMillis());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstm!=null){
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	

}
