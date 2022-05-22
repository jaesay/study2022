package com.jaesay.designpatterns._03_behavioral_patterns._16_iterator._02_after;

import com.jaesay.designpatterns._03_behavioral_patterns._16_iterator._01_before.Post;

import java.util.Iterator;
import java.util.List;

// 내부 집합 구조가 변경될 수 있거나 여러 순회방법을 제공해야 된다면 고려해보자
public class Client {

    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("디자인 패턴 게임");
        board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        // 들어간 순서대로 순회하기
        List<Post> posts = board.getPosts();
        Iterator<Post> iterator = posts.iterator(); // iterator
        System.out.println(iterator.getClass()); // concrete iterator

        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }

        // 가장 최신 글 먼저 순회하기
        Iterator<Post> recentPostIterator = board.getRecentPostIterator();
        while(recentPostIterator.hasNext()) {
            System.out.println(recentPostIterator.next().getTitle());
        }
    }
}
