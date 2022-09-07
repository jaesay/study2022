package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import java.io.IOException;

public class ReviewerDashboard extends Dashboard {

    // 비슷하지만 일부값(eventId)만 다른 경우 "함수 매개변수화하기(Parameterize Function)" 리팩토링을 적용한 이후에 이 방법을 사용할 수 있다.
    public void printReviewers() throws IOException {
        super.printUsernames(30);
    }
}
