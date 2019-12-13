package com.example.guide.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-13 13:22
 **/
@Component
@Slf4j
public class MyStarter implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    log.info("---------after--------->");
  }
}
