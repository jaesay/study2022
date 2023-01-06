package com.example.dynamicproxyexample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class GreetingServiceTest {

  GreetingService greetingService = (GreetingService) Proxy.newProxyInstance(
      GreetingService.class.getClassLoader(), new Class[]{GreetingService.class},
      new InvocationHandler() {
        DefaultGreetingService greetingService = new DefaultGreetingService();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          if (method.getName().equals("hello")) {
            System.out.println("aaaa");
            Object invoke = method.invoke(greetingService, args);
            System.out.println("bbbb");
            return invoke;
          }

          return method.invoke(greetingService, args);
        }
      });

  @Test
  void test() {
    this.greetingService.hello();
    this.greetingService.hi();
  }
}