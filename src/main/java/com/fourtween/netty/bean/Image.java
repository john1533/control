package com.fourtween.netty.bean;

public class Image extends BaseBean{

	private long length;
	private byte[] data;
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
