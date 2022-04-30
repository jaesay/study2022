package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

// 필드이름은 더 많이 참조되기 떄문에 더 중요..
// username보다는 reviewer가 더 적절해본다.
public record StudyReview(String reviewer, String review) {
}
