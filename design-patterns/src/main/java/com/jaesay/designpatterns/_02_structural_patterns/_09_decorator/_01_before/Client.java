package com.jaesay.designpatterns._02_structural_patterns._09_decorator._01_before;

public class Client {
    // trim과 spam을 같이 사용하고 싶으면??
    private CommentService commentService;

    public Client(CommentService commentService) {
        this.commentService = commentService;
    }

    public void writeComment(String comment) {
        commentService.addComment(comment);
    }
}
