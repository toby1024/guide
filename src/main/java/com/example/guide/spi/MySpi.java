package com.example.guide.spi;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-05-25 20:55
 **/
public class MySpi implements ISpiDemo{
  @Override
  public String helloWorld(String name) {
    return "Hello my " + name;
  }
}
