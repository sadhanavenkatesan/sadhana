package com.mynetty.chatserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

public class NettyClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

	@Override
	public void messageReceived(ChannelHandlerContext arg0, String arg1)
			throws Exception {
		System.out.println(arg1);
	}

}
