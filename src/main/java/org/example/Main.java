package org.example;

import org.example.database.PostDatabase;
import org.example.post.Post;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        int id;
        String command, number;
        Scanner sc = new Scanner(System.in);

        PostDatabase database = new PostDatabase();
        while (run) {
            System.out.println("명령어 > ");
            command = sc.next();

            switch (command) {
                case "종료":
                    System.out.println("프로그램이 종료됩니다.");
                    run = false;
                    break;
                case "작성":
                    database.create();
                    break;
                case "조회":
                    System.out.println("어떤 게시물을 조회할까요?");
                    number = sc.next();
                    id = Integer.parseInt(number.substring(0, 1));
                    Post post = database.read(id);
                    if (post != null) {
                        String title = post.getTitle();
                        String content = post.getContent();
                        System.out.println("제목: " + title);
                        System.out.println("내용: " + content);
                    }
                    break;
                case "삭제":
                    System.out.println("어떤 게시물을 삭제할까요?");
                    number = sc.next();
                    id = Integer.parseInt(number.substring(0, 1));
                    database.delete(id);
                    break;
                case "수정":
                    System.out.println("어떤 게시물을 수정할까요?");
                    number = sc.next();
                    id = Integer.parseInt(number.substring(0, 1));
                    database.update(id);
                    break;
                case "목록":
                    database.traverse();
                    break;
                default:
                    System.out.println("존재하지 않는 명령어입니다. ");
            }
        }
    }
}