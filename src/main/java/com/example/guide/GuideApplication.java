package com.example.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class GuideApplication {

  public static void main(String[] args) {
    SpringApplication.run(GuideApplication.class, args);
  }

}
