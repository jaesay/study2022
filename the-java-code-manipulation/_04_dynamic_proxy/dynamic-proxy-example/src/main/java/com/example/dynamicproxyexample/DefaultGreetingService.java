package com.example.dynamicproxyexample;

public class DefaultGreetingService implements GreetingService {

  @Override
  public void hello() {
    System.out.println("hello");
  }

  @Override
  public void hi() {
    System.out.println("hi");
  }
}
