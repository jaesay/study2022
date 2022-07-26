package com.jaesay.designpatterns._03_behavioral_patterns._15_interpreter._02_after;

import java.util.Map;

public interface PostfixExpression {
    int interpret(Map<Character, Integer> context);

    // 여기에 static method로 해도 됨
    static PostfixExpression plus(PostfixExpression left, PostfixExpression right) {
        return context -> left.interpret(context) + right.interpret(context);
    }
}
