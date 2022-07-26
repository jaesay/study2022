package com.jaesay.designpatterns._02_structural_patterns._09_decorator._02_after;

// 데코레이터도 코멘트서비스(Component) 상속
public class CommentDecorator implements CommentService {

    private CommentService commentService;

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}
