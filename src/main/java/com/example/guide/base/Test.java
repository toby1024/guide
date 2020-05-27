package com.example.guide.base;

import org.springframework.beans.BeanUtils;

import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-04-29 00:06
 **/
public class Test {
  public static void main(String[] args) {
    User user = new User();
    user.setUsername("test");
    Test test = new Test();
    test.test(user);
    test.test(user);
  }

  public void test(User a){
    User user = new User();
    BeanUtils.copyProperties(a, user);

    System.out.println(user.getUsername());
    user.setUsername(UUID.randomUUID().toString());
    System.out.println(user.getUsername());
  }
}
