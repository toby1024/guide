package com.example.guide.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-13 13:36
 **/
@Slf4j
@Component
public class Refreshed implements ApplicationListener<ContextRefreshedEvent> {
  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("==========refreshed------");
  }
}
