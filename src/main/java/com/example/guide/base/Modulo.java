package com.example.guide.base;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2020-04-20 10:55
 **/
public class Modulo {

  public static void main(String[] args) {
    Integer a = 10;
    System.out.println(3 & 7);
    System.out.println(3 % 7);
    Modulo modulo = new Modulo();
    System.out.println(a);
    modulo.testA(a);
    System.out.println(a);
    modulo.testA(a);
    System.out.println(a);
  }

  public void testA(Integer a){
    System.out.println(a);
    a--;
    System.out.println(a);
  }
}
