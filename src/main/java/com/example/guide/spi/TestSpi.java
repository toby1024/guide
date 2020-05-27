package com.example.guide.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-05-25 20:55
 **/
public class TestSpi {

  public static void main(String[] args) {
    ServiceLoader<ISpiDemo> load = ServiceLoader.load(ISpiDemo.class);
    Iterator<ISpiDemo> iterator = load.iterator();
    while(iterator.hasNext()){
      System.out.println(iterator.next().helloWorld("baby"));
    }
  }
}
