package com.jaesay.designpatterns._02_structural_patterns._06_adapter._03_java;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class SpringAdapter {
    DispatcherServlet dispatcherServlet = new DispatcherServlet();
    HandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
}
