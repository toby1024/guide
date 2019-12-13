package com.example.guide.java8.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author jason
 * @create 2019-04-22 15:08
 */
public class Main1 {
  private static Random rand = new Random();
  private static long t = System.currentTimeMillis();

  static int getMoreData() {
    System.out.println("begin to start compute");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
    return rand.nextInt(1000);
  }

  public static void main(String[] args) throws Exception {
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main1::getMoreData);
    Future<Integer> f = future.whenComplete((v, e) -> {
      System.out.println("v:" + v);
//      e.printStackTrace();
    });
    System.out.println("final future:" + future.get());
    System.out.println("final f:" + f.get());
    System.out.println("main thread working");
  }
}
