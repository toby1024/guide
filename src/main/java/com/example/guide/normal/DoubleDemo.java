package com.example.guide.normal;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-03-09 10:52
 **/
public class DoubleDemo {

  public static void main(String[] args) {
    Integer a = 99;
    double d = a / 1.00;
    double d2 = Double.valueOf(a);
    System.out.println(d);
    System.out.println(d2);

    Integer b = 0;
    double d3 = b /1.00;
    System.out.println(d3);
    System.out.println(Double.valueOf(b));
  }
}
