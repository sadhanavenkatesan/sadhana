package com.mynetty.chatserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		
		pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder()); 
		pipeline.addLast("handler",new NettyServerHandler());
		
		/*pipeline.addLast(LineBasedFrameDecoder.class.getName(),
                new LineBasedFrameDecoder(256));

        pipeline.addLast(StringDecoder.class.getName(),
                new StringDecoder(CharsetUtil.UTF_8));

        pipeline.addLast(JsonDecoder.class.getName(),
        		 new JsonDecoder<>(Person.class));

        pipeline.addLast("stdoutHandler",
                new ChannelInboundMessageHandlerAdapter<Person>() {
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, Person person) throws Exception {
                        System.out.println(
                                "Your name is " + person.getFirstname() + " " + person.getLastname() + "!"
                        );
                    }
                }
);*/
		
	}

}
