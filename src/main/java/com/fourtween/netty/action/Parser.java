package com.fourtween.netty.action;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.fourtween.netty.bean.BaseBean;
import com.fourtween.netty.bean.Breath;
import com.fourtween.netty.bean.Image;
import com.fourtween.netty.bean.Register;

public class Parser {
	
	
	
	public static void parse(byte[] bytes){
		BaseBean bean;
		DataInputStream din = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			int type = din.readInt();
			String imei = din.readUTF();
			switch(type){
				case 0x00:
					bean = new Register();
					bean.setType(type);
					bean.setImei(imei);
					((Register)bean).setWidth(din.readInt());
					((Register)bean).setHeight(din.readInt());
					din.readLong();
					((Register)bean).setModel(din.readUTF());
				case 0x01:
					bean = new Breath();
					bean.setType(type);
					bean.setImei(imei);
				case 0x02:
					bean = new Image();
					bean.setType(type);
					bean.setImei(imei);
					long length = din.readLong();
					((Image)bean).setLength(length);
					((Image)bean).setData(new byte[(int) length]);
					din.readFully(((Image)bean).getData());
				
				bean.doExecute();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(din!=null){
				try {
					din.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

}
