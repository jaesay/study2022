package me.whiteship.refactoring._01_smell_mysterious_name._02_rename_variable;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudyDashboard {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // 선언부와 변수명이 다르다 => context 상 더 적절한 한개로 통일
        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            usernames.add(review.getUserName());
            this.reviews.add(review.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        // 메소드 레퍼런스 또는 앞글자만 따서 (람다는 변수의 스콥이 좁고 안에 들어가는 데이터가 명확하기 떄문), 아니면 명시적으로
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
