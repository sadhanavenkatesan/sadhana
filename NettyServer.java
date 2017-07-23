package com.mynetty.chatserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

	private final int port;
	
	public static void main(String args[]) throws InterruptedException{
		new NettyServer(8080).run();
	}
	
	public NettyServer(int port) {
		this.port= port;
	}

	public void run() throws InterruptedException{
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup wrokerGroup = new NioEventLoopGroup();

	try{
		ServerBootstrap bootstrap = new ServerBootstrap()
		.group(bossGroup,wrokerGroup)
				.channel(NioServerSocketChannel.class).childHandler(new NettyServerInitializer()).option(ChannelOption.SO_BACKLOG, 5)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.bind(port).sync().channel().closeFuture().sync();
	}
	finally{
		bossGroup.shutdownGracefully();
		wrokerGroup.shutdownGracefully();
	}
}
}


