package com.example.guide.java8.future;

/**
 * @author jason
 * @create 2019-04-22 14:05
 */
public class Result {

  private String content;
  private long sleetTime;

  public Result() {
  }

  public Result(String content) {
    this.content = content;
  }

  public Result(String content, long sleetTime) {
    try {
      System.out.println(content + " is sleep "+ sleetTime);
      Thread.sleep(sleetTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.content = content;
    this.sleetTime = sleetTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void prientContent(){
    System.out.println("content is:" + content);
  }
}
