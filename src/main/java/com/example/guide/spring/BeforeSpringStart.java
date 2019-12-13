package com.example.guide.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-13 13:29
 **/
@Slf4j
@Component
public class BeforeSpringStart implements ApplicationListener<ContextStartedEvent> {
  @Override
  public void onApplicationEvent(ContextStartedEvent event) {
    log.info("---------------before spring start---");
  }
}
