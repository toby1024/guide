package com.example.guide.guava;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: java8_demo
 * @description:
 * @author: Jason
 * @date: 2019-10-10 16:44
 **/
public class OrderingDemo {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>(10);

    for (int i = 0; i < 10; i++) {
      list.add(UUID.randomUUID().toString());
    }

    String min = Ordering.natural().min(list);
    System.out.println(min);
    String max = Ordering.natural().max(list);
    System.out.println(max);

    System.out.println("--->");
    list.forEach(System.out::println);
    System.out.println("--->");
    List<String> strings = Ordering.natural().sortedCopy(list);
    strings.forEach(System.out::println);

    System.out.println("-->");
    List<String> strings1 = Ordering.natural().reverse().sortedCopy(list);
    strings1.forEach(System.out::println);
  }
}
