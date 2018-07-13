package com.fourtween.netty.bean;

import java.io.UnsupportedEncodingException;

import com.fourtween.netty.ChannelHolder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public abstract class Response {
	
	protected int type;
	
	protected String client;
	
	public Response(int type,String client) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.client = client;
	}
	
	protected ByteBuf pingMessage = Unpooled.buffer();
	
	protected ByteBuf generateByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        pingMessage.writeBytes(req);
        return pingMessage;
    }
	
	protected ByteBuf generateByteBuf(int intValue){

        pingMessage.writeInt(intValue);
        return pingMessage;
    }
	
	protected ByteBuf generateByteBuf(long longValue){

        pingMessage.writeLong(longValue);
        return pingMessage;
    }
	
	
	protected ByteBuf generateByteBuf(short shortValue) {

        pingMessage.writeShort(shortValue);
        return pingMessage;
        
    }
	
	abstract protected void initContent();
	
		
	protected void send2Client(){
		generateByteBuf(this.type);
		try {
			generateByteBuf(this.client);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initContent();
		ChannelHolder.getCtx(this.client).writeAndFlush(pingMessage);
		
	}
}
