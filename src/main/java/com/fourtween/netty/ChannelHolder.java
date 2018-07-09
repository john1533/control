package com.fourtween.netty;

import java.util.HashMap;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHolder {
	private static ChannelHolder sInstance = new ChannelHolder();
	
	private HashMap<String,ChannelHandlerContext> ctxCache;
	
	public static ChannelHolder getInstance(){
		synchronized (sInstance) {
			if(sInstance==null){
				sInstance = new ChannelHolder();
			}
			if(sInstance.ctxCache == null){
				sInstance.ctxCache = new HashMap();
			}
			return sInstance;
		}		
	}
	
	public static void addCtx(String key,ChannelHandlerContext ctx){
		ChannelHolder instance = getInstance();
		if(!instance.ctxCache.containsKey(key)){
			instance.ctxCache.put(key, ctx);
		}
	}
	
	public static void rmCtx(String key){
		ChannelHolder instance = getInstance();
		if(instance.ctxCache.containsKey(key)){
			instance.ctxCache.remove(instance.ctxCache.get(key));
		}
	}
	
	public static void traceCtx(){
		ChannelHolder instance = getInstance();
		
		for(String key:instance.ctxCache.keySet()){
			System.out.println(key+":"+instance.ctxCache.get(key));
		}
	} 
	
}
