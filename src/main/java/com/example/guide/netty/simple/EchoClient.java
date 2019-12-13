package com.example.guide.netty.simple;

import com.example.guide.zk.ZkService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

import static com.example.guide.netty.simple.Constant.ZK_SERVER;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 15:14
 **/
public class EchoClient {

  static final int SIZE = 256;

  public static void main(String[] args) throws Exception {

    ZkService zkService = new ZkService(ZK_SERVER);
    String s = zkService.listNetty();
    String host = s.split(":")[0];
    String port = s.split(":")[1];

    Bootstrap bootstrap = new Bootstrap();
    NioEventLoopGroup group = new NioEventLoopGroup();

    bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel ch) {
            ch.pipeline().addLast(new StringEncoder());
          }
        });

    Channel channel = bootstrap.connect(host, Integer.parseInt(port)).channel();

    System.out.println(String.format("connect netty host: %s, port: %s, channel id: %s", host, port, channel.id()));
    while (true) {
      channel.writeAndFlush(new Date() + ": hello world!");
      Thread.sleep(2000);
    }
  }
}
