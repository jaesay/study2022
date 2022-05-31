package com.jaesay.designpatterns._03_behavioral_patterns._20_state._02_after;

// 객체 내부 상태 변경에 따라 객체의 행동이 달라지는 패턴
// Context(OnlineCourse): 사용자가 관심 있는 인터페이스를 정의합니다. 객체의 현재 상태를 정의한 ConcreteState 서브클래스의 인스턴스를 유지/관린합니다.
// State(State): Context의 각 상태별로 필요한 행동을 캡슐화하여 인터페이스로 정의합니다.
// Concrete state: 각 서브클래스들은 Context의 상태에 따라 처리되어야 할 실제 행동을 구현합니다.
public class Client {

    public static void main(String[] args) {
        OnlineCourse onlineCourse = new OnlineCourse();
        Student student = new Student("whiteship");
        Student keesun = new Student("keesun");
        keesun.addPrivate(onlineCourse);

        onlineCourse.addStudent(student);

        onlineCourse.changeState(new Private(onlineCourse));

        onlineCourse.addReview("hello", student);

        onlineCourse.addStudent(keesun);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getReviews());
        System.out.println(onlineCourse.getStudents());
    }
}
