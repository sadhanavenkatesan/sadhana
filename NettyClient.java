package com.mynetty.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.InetSocketAddress;

import org.json.JSONException;
import org.json.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyClient {

	

	
	public static void main(String args[]) throws IOException, InterruptedException, JSONException{
	
		new NettyClient("localhost",8080).run();	
	}
	
	private final String host;
	
	private final int port;
	
	public NettyClient(String host,int port){
		this.host = host;
		this.port = port;
	
	}
	
	public void run() throws IOException, InterruptedException, JSONException{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap bootstrap = new Bootstrap()
			.group(group)
			.channel(NioSocketChannel.class)
			.handler(new NettyClientInitializer());
			
			
			Channel channel = bootstrap.connect(host,port).sync().channel();
//		    System.out.print(jsonText);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			JSONObject obj = new JSONObject();

		    obj.put("firstname","Sathish");
		    obj.put("lastname","Kumar");
		  		    		    
		    channel.write(obj.toString()+ "\r\n");
		    
			while(true){
				channel.write(in.readLine() + "\r\n");
			}
		}finally{
			group.shutdownGracefully();
		}
	}
	
}
