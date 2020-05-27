package com.example.guide.thread.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-05-27 11:56
 **/
public class SynchronizedDemo {

  public synchronized void sync1(){
    System.out.println("this is synchronized 1");
    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("this is synchronized 1 again");
  }

  public synchronized void sync2(){
    System.out.println("this is synchronized 2");
    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("this is synchronized 2 again");
  }

  public void test(){
    System.out.println("普通方法");
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("普通方法 2 again");
  }


  public static void main(String[] args) {
    CyclicBarrier barrier  = new CyclicBarrier(3);
    SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
    Thread thread1 = new Thread(() -> {
      try {
        barrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      synchronizedDemo.sync1();
    });

    Thread thread2 = new Thread(() -> {
      try {
        barrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      synchronizedDemo.sync2();
    });

    Thread thread3 = new Thread(() -> {
      try {
        barrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      synchronizedDemo.test();
    });

    thread1.start();
    thread2.start();
    thread3.start();

  }
}
