package com.example.guide.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 15:16
 **/
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
  private final ByteBuf firstMessage;

  /**
   * Creates a client-side handler.
   */
  public EchoClientHandler() throws InterruptedException {
    firstMessage = Unpooled.buffer(EchoClient.SIZE);
    while(true) {

      firstMessage.writeBytes(String.format("hello %s,%s", "server", System.currentTimeMillis()).getBytes());
      System.out.println("send message-->");
      Thread.sleep(2000L);
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(firstMessage);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ctx.write(msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }
}
