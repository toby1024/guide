package com.example.guide.java8.lambda;

/**
 * @author jason
 * @create 2019-04-18 16:18
 */
public class Test {

  private String name = "test";

  public Test() {
  }

  public Test(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString(){
    return "name:" + this.name;
  }
}
