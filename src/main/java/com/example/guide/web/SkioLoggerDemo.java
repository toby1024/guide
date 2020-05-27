package com.example.guide.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-03-23 15:26
 **/
@RestController
@RequestMapping("/logger")
public class SkioLoggerDemo {

  @PostMapping("/")
  public Form postTest(@RequestBody Form form) {

    System.out.println(form);

    return form;
  }

  @GetMapping("/")
  public String getTest(String username) {
    return "hello " + username;
  }
}
