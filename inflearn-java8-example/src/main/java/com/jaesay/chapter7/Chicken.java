package com.jaesay.chapter7;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE_PARAMETER) // generic
@Target(ElementType.TYPE_USE) // all types
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value();
}
