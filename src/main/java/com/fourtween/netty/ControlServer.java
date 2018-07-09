package com.fourtween.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ControlServer{
	private int port;
	public ControlServer(int port) {
        this.port = port;
        bind();
    }

    private void bind() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024); // ������
            bootstrap.option(ChannelOption.TCP_NODELAY, true); // ���ӳ٣���Ϣ��������
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); // ������
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                        throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast(new NettyServerHandler2());// ���NettyServerHandler����������Server�˽��պʹ�����Ϣ���߼�
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            if (channelFuture.isSuccess()) {
                System.err.println("����Netty����ɹ����˿ںţ�" + this.port);
            }
            // �ر�����
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            System.err.println("����Netty�����쳣���쳣��Ϣ��" + e.getMessage());
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ControlServer(9090);
    }


}
