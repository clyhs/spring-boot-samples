package com.abigfish.nettyserver.channel;

import com.abigfish.nettyserver.codec.MsgDecoder;
import com.abigfish.nettyserver.codec.MsgEncoder;
import com.abigfish.nettyserver.handler.HeartBeatSimpleHandler;
import com.abigfish.nettyserver.handler.NettyServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * netty服务初始化器
 * @date Apr 19, 2021 10:53:17 PM
 *
 * @author 大鱼
 *
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //添加编解码
    	socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0));
        socketChannel.pipeline().addLast("decoder", new MsgDecoder());
        socketChannel.pipeline().addLast("encoder", new MsgEncoder());
        // socketChannel.pipeline().addLast(new NettyServerHandler());
        socketChannel.pipeline().addLast(new HeartBeatSimpleHandler());
    }
}