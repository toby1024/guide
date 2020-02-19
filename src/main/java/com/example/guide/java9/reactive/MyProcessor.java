package com.example.guide.java9.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-02-19 12:40
 **/
public class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {


  private Flow.Subscription subscription;

  @Override
  public void onSubscribe(Flow.Subscription subscription) {

    this.subscription = subscription;

    this.subscription.request(1L);
  }

  @Override
  public void onNext(Integer data) {
    System.out.println("处理接收数据数据：" + data);

    if (data < 0) {
      System.out.println("小于0的数据" + data + "发布出去");
      this.submit("数据" + data + "小于0");
    }

    this.subscription.request(1L);
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
    this.subscription.cancel();
  }

  @Override
  public void onComplete() {
    System.out.println("----------over----------");
    this.close();
  }
}
