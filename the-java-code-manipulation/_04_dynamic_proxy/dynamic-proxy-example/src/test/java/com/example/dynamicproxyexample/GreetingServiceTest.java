package com.example.dynamicproxyexample;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

class GreetingServiceTest {

  @Test
  void dynamicJdkProxyTest() {
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

    greetingService.hello();
    greetingService.hi();
  }

  @Test
  void cglibTest() {
    MethodInterceptor handler = new MethodInterceptor() {
      DefaultGreetingService greetingService = new DefaultGreetingService();

      @Override
      public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
          throws Throwable {

        if (method.getName().equals("hello")) {
          System.out.println("aaaa");
          Object invoke = method.invoke(greetingService, args);
          System.out.println("bbbb");
          return invoke;
        }

        return method.invoke(greetingService, args);
      }
    };
    DefaultGreetingService greetingService = (DefaultGreetingService) Enhancer.create(DefaultGreetingService.class, handler);

    greetingService.hello();
    greetingService.hi();
  }

  @Test
  void byteBuddyTest()
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<? extends DefaultGreetingService> proxyClass = new ByteBuddy().subclass(
            DefaultGreetingService.class)
        .method(named("hello")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
          DefaultGreetingService greetingService = new DefaultGreetingService();

          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("aaaa");
            Object invoke = method.invoke(greetingService, args);
            System.out.println("bbbb");
            return invoke;
          }
        }))
        .make().load(DefaultGreetingService.class.getClassLoader()).getLoaded();
    DefaultGreetingService greetingService = proxyClass.getConstructor(null).newInstance();

    greetingService.hello();
    greetingService.hi();
  }
}