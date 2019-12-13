package com.example.guide.java8.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author jason
 * @create 2019-04-18 16:15
 */
public class SupplierDemo {

  public static void main(String[] args) {
    SupplierDemo demo = new SupplierDemo();
    demo.test(() -> "test".length());
    demo.test(() -> "test".toUpperCase());

    demo.test(() -> new Test("Toby"));

    Test test = new Test();
    demo.test(() -> test);

    demo.test(Test::new);

    demo.test(Test::new, (t) -> t.setName("new Name"));
  }

  private void test(Supplier x) {
    System.out.println(x.get());
  }

  private void test(Supplier<Test> x, Consumer<Test> y){
    System.out.println("----------->");
    Test t = x.get();

    System.out.println(t);
    y.accept(t);
    System.out.println(t);
  }
}
