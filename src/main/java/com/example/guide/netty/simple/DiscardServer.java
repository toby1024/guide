package com.example.guide.netty.simple;

import com.example.guide.zk.ZkService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.example.guide.netty.simple.Constant.NETTY_PORT;
import static com.example.guide.netty.simple.Constant.ZK_SERVER;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 15:10
 **/
public class DiscardServer {



  public static void main(String[] args) throws InterruptedException, UnknownHostException {
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    NioEventLoopGroup boos = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();
    serverBootstrap
        .group(boos, worker)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel ch) {
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
              @Override
              protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                System.out.println(msg);
              }
            });
          }
        })
        .bind(NETTY_PORT).sync();

    System.out.println("----->zk");
    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println(localHost.getHostAddress());
    ZkService zkService = new ZkService(ZK_SERVER);
    zkService.register(localHost.getHostAddress().concat(":").concat(String.valueOf(NETTY_PORT)));
  }
}
