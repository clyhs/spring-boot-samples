package com.abigfish.nettyclient.channel;

import com.abigfish.nettyclient.codec.MsgDecoder;
import com.abigfish.nettyclient.codec.MsgEncoder;
import com.abigfish.nettyclient.handler.EchoClientHandler;
import com.abigfish.nettyclient.handler.NettyClientHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
    	socketChannel.pipeline().addLast(new IdleStateHandler(0, 10, 0));
        socketChannel.pipeline().addLast("decoder", new MsgDecoder());
        socketChannel.pipeline().addLast("encoder", new MsgEncoder());
        socketChannel.pipeline().addLast(new EchoClientHandler());
    }
}