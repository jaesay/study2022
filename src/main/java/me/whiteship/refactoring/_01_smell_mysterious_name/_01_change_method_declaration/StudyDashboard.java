package me.whiteship.refactoring._01_smell_mysterious_name._01_change_method_declaration;

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

    // 좋은 함수이름을 정하는 방법? 함수에 주석을 작성한 담 주석을 함수 이름으로 만들어 보자
//    /** 리뷰 이슈에 작성되어 있는 리뷰어와 리뷰 목록을 읽어온다.
//     * @param issue
//     * @throws IOException
//     */
    // 리뷰를 가져올 이슈는 이미 정해져 있다 => 파라미터를 제거하자
    // 어떤 데이터를 파라미터로 넘겨줄지도 상황에 따라 다름(전체, 필요한 정보만)
    private void studyReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment : comments) {
            usernames.add(comment.getUserName());
            reviews.add(comment.getBody());
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
        studyDashboard.studyReviews();
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
