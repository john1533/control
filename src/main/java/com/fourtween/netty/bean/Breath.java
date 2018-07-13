package com.fourtween.netty.bean;

public class Breath extends BaseBean{
	
	@Override
	public void doExecute() {
		// TODO Auto-generated method stub
		super.doExecute();
		
		Response response = new BreathRsp(this.type,this.imei);
		response.send2Client();
	}

}
