package com.example.guide.netty.spring;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-12 14:34
 **/
@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitializer extends ChannelInitializer<SocketChannel> {

  @Autowired
  StringDecoder stringDecoder;

  @Autowired
  StringEncoder stringEncoder;

  @Autowired
  ServerHandler serverHandler;

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("decoder", stringDecoder);
    pipeline.addLast("handler", serverHandler);
    pipeline.addLast("encoder", stringEncoder);
  }

  public StringDecoder getStringDecoder() {
    return stringDecoder;
  }

  public void setStringDecoder(StringDecoder stringDecoder) {
    this.stringDecoder = stringDecoder;
  }

  public StringEncoder getStringEncoder() {
    return stringEncoder;
  }

  public void setStringEncoder(StringEncoder stringEncoder) {
    this.stringEncoder = stringEncoder;
  }

  public ServerHandler getServerHandler() {
    return serverHandler;
  }

  public void setServerHandler(ServerHandler serverHandler) {
    this.serverHandler = serverHandler;
  }

}
