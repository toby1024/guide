package com.example.guide.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: java8_demo
 * @description:
 * @author: Jason
 * @date: 2019-10-10 10:46
 **/
public class RateLimiterDemo {

  public static void main(String[] args) {
    RateLimiter rateLimiter = RateLimiter.create(3);

    List<String> list = new ArrayList<>(1000);

    for (int i = 0; i < 1000; i++) {
      list.add(UUID.randomUUID().toString());
    }

    list.parallelStream().forEach(str -> {
      rateLimiter.acquire();
      System.out.println(LocalDateTime.now() + "==" + str);
    });
  }

}
