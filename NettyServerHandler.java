package com.mynetty.chatserver;

import org.json.JSONObject;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

public class NettyServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	public static final ChannelGroup channels = new DefaultChannelGroup();
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		
		for(Channel channel : channels){
			
			channel.write("[server] :" +  incoming.remoteAddress()  + " has joined\n");
		}
		channels.add(ctx.channel());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		for(Channel channel : channels){
			channel.write("[server] :" + incoming.remoteAddress() + " has left\n");
		}
		channels.remove(ctx.channel());
		
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, String person)
			throws Exception {
		Channel incoming = ctx.channel();
		for(Channel channel : channels){
			if(channel != incoming){
			channel.write("[NettyServer] :" + incoming.remoteAddress() + " _____   " +person +  "\n" );
			JSONObject obj=new JSONObject(person);			
			if (obj instanceof JSONObject) {
				channel.write("[NettyServer] :" + incoming.remoteAddress() + "firstname==" +obj.getString("firstname")+"\n" );
				channel.write("[NettyServer] :" + incoming.remoteAddress() + "lastname==" +obj.getString("lastname")+"\n" );
			}	
			}
		}
		
	}
	
	
}
