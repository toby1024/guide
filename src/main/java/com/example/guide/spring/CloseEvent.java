package com.example.guide.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-13 13:31
 **/
@Slf4j
@Component
public class CloseEvent implements ApplicationListener<ContextClosedEvent> {

  @Override
  public void onApplicationEvent(ContextClosedEvent event) {
    log.info("-=----close ------");
  }
}
