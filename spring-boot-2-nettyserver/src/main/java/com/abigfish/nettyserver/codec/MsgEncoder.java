package com.abigfish.nettyserver.codec;

import com.abigfish.nettyserver.message.TcpMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<TcpMessage> {

	@Override
	protected void encode(ChannelHandlerContext clc, TcpMessage tm, ByteBuf byteBuf) throws Exception {
		// TODO Auto-generated method stub
		byteBuf.writeLong(tm.getId()) ;
        byteBuf.writeBytes(tm.getContent().getBytes()) ;
	}

}
