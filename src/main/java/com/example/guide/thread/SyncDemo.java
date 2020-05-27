package com.example.guide.thread;

import lombok.SneakyThrows;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-04-22 10:06
 **/
public class SyncDemo {

  public static void main(String[] args) {
    PubObj o = new PubObj();
    for (int i = 0; i < 100; i++) {
      int finalI = i;
      Thread thread = new Thread(() -> {
        SyncDemo d = new SyncDemo();
        d.test("thread: " + finalI, o);
        d.testB("thread: " + finalI, o);
      }, "thread: " + i);

      System.out.println(thread.getName());
      thread.start();
    }
  }

  @SneakyThrows
  public synchronized void test(String thread,PubObj o) {
    Thread.sleep(5000);
    o.setA(o.getA() + 1);
    System.out.println(thread + "test：" + o.getA());
  }

  public synchronized void testB(String thread,PubObj o) {
    o.setA(o.getA() + 1);
    System.out.println(thread + "testB：" + o.getA());
  }

}
