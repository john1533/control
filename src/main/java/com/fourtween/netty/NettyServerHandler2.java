package com.fourtween.netty;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler2 extends ChannelInboundHandlerAdapter  {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("active:"+ctx);
		super.channelActive(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inactive:"+ctx);
		super.channelInactive(ctx);
	}
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	System.out.println("channelRead:"+ctx);
        ByteBuf buf = (ByteBuf) msg;
        
        String recieved = getMessage(buf);
        System.err.println("���������յ��ͻ�����Ϣ��" + recieved);
        
        try {
            ctx.writeAndFlush(getSendByteBuf("��ã��ͻ���"));
            System.err.println("�������ظ���Ϣ����ã��ͻ���");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*
     * ��ByteBuf�л�ȡ��Ϣ ʹ��UTF-8���뷵��
     */
    private String getMessage(ByteBuf buf) {

        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }


}
