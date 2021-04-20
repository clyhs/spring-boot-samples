package com.abigfish.nettyserver.codec;

import java.util.List;

import com.abigfish.nettyserver.message.TcpMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext clc, ByteBuf byteBuf, List<Object> list) throws Exception {
		// TODO Auto-generated method stub
		long id = byteBuf.readLong();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String content = new String(bytes);
        TcpMessage tm = new TcpMessage();
        tm.setId(id);
        tm.setContent(content);
        list.add(tm);
	}

}
