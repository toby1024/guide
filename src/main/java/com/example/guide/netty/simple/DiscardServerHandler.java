package com.example.guide.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 15
 **/
public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
    try {
      System.out.println("-----");
      System.out.println(s);
    } finally {
      ReferenceCountUtil.release(s);
    }
  }
}
