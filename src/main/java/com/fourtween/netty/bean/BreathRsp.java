package com.fourtween.netty.bean;

import java.io.UnsupportedEncodingException;

public class BreathRsp extends Response{

	public BreathRsp(int type,String client) {
		super(type,client);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initContent() {
		// TODO Auto-generated method stub
		generateByteBuf(System.currentTimeMillis());
	}
	
}
