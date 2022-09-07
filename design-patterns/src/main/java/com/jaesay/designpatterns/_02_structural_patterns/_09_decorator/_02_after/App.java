package com.jaesay.designpatterns._02_structural_patterns._09_decorator._02_after;

public class App {

    private static boolean enabledSpamFilter = true;

    private static boolean enabledTrimming = true;

    public static void main(String[] args) {
        CommentService commentService = new DefaultCommentService();

        if (enabledSpamFilter) {
            // 1번 랩핑
            commentService = new SpamFilteringCommentDecorator(commentService);
        }

        if (enabledTrimming) {
            // 2번 랩핑 => 조합 가능
            commentService = new TrimmingCommentDecorator(commentService);
        }

        Client client = new Client(commentService);
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }
}
