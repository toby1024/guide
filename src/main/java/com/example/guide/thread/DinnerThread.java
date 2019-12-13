package com.example.guide.thread;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-06 16:10
 **/
public class DinnerThread implements Runnable {
  private String name;
  private Dinner.Friend friend;
  private String message;

  public DinnerThread(String name, Dinner.Friend friend, String message) {
    this.name = name;
    this.friend = friend;
    this.message = name + "-->" +message;
  }

  @Override
  public void run() {
    System.out.println(name + ": running....");
    Dinner d = new Dinner();
    d.call(friend, message);
  }
}
