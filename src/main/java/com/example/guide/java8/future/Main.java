package com.example.guide.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author jason
 * @create 2019-04-22 14:04
 */
public class Main {

  public static void main(String[] args) {
    final CompletableFuture<Result> result1 = CompletableFuture.supplyAsync(() -> new Result("result1",5000L));

    final CompletableFuture<Result> result2 = CompletableFuture.supplyAsync(() -> new Result("result2", 2000L));

    CompletableFuture<Object> future = CompletableFuture.anyOf(result1, result2);
    try {
      Result result = (Result) future.get();
      result.prientContent();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
