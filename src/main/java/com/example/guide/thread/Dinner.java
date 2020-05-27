package com.example.guide.thread;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: guide
 * @description: 打电话给A： 今晚喝酒，请带肉来
 * 打电话给A： 今晚吃肉，请带酒来
 * 打电话给C： 今晚喝酒吃肉，请带青菜来
 * 打电话给D： 今晚喝酒吃肉，请带花生米来
 * 打电话给E： 今晚喝酒吃肉，请带妹子来
 * @author: Jason
 * @date: 2019-12-06 14:19
 **/
public class Dinner {
  static List<String> friends = Arrays.asList("李四", "张三", "王二", "赵六", "钱久");
  static List<String> works = Arrays.asList("带肉", "青菜", "带酒", "带花生米", "带妹子");

  static CountDownLatch countDownLatch = new CountDownLatch(5);

  public static void main(String[] args) throws InterruptedException {
    System.out.println("今晚聚餐。。。");

    for (int i = 0; i < friends.size(); i++) {
      String threadName = "thread" + i;
      DinnerThread thread = new DinnerThread(threadName, new Friend(friends.get(i)), works.get(i));
      new Thread(thread).start();

    }

    if (countDownLatch.await(10, TimeUnit.SECONDS)) {
      System.out.println("---喝酒吃肉");
    } else {
      System.out.println("---GG");
    }
  }

  public boolean call(Friend friend, String message) {
    try {
      friend.work(message);
      countDownLatch.countDown();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  static class Friend {
    private String name;

    public Friend(String name) {
      this.name = name;
    }

    public void work(String message) throws Exception {
      if (Objects.isNull(this.name)) {
        throw new Exception("no body..");
      }
      System.out.println(this.name + message);
    }
  }
}
