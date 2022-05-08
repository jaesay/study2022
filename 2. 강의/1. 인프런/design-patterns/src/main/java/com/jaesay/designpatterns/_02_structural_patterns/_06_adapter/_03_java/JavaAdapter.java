package com.jaesay.designpatterns._02_structural_patterns._06_adapter._03_java;

import java.util.Arrays;
import java.util.List;

public class JavaAdapter {
    // target interface: List, adapter: Arrays.asList, adaptee: object array(varargs)
    List<String> strings = Arrays.asList("a", "b", "c");
}
