package com.abigfish.nettyserver.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abigfish.nettyserver.message.TcpMessage;
import com.abigfish.nettyserver.utils.NettySocketHolder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class HeartBeatSimpleHandler extends SimpleChannelInboundHandler<TcpMessage> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatSimpleHandler.class);
    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new TcpMessage(123456L, "pong").toString(), CharsetUtil.UTF_8));

    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettySocketHolder.remove((NioSocketChannel) ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                LOGGER.info("已经5秒没有收到信息！");
                //向客户端发送消息
                ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TcpMessage tm) throws Exception {
        LOGGER.info("收到tm={}", tm);
        //我们调用writeAndFlush（Object）来逐字写入接收到的消息并刷新线路
        ctx.writeAndFlush(tm);
        //保存客户端与 Channel 之间的关系
        NettySocketHolder.put(tm.getId(), (NioSocketChannel) ctx.channel());
    }
}