package com.example.guide.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-13 13:25
 **/
@Component
@Slf4j
public class BeforeStart implements ApplicationRunner {
  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("----before-----");
  }
}
