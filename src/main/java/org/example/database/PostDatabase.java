package org.example.database;

import org.example.post.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostDatabase implements Database<Post>{
    private List<Post> posts;
    private int nextId;

    public PostDatabase() {
        this.posts = new ArrayList<>();
        this.nextId = 1;
    }


    @Override
    public void create() {
        Scanner sc = new Scanner(System.in);
        System.out.println("제목: ");
        String newTitle = sc.next();
        System.out.println("내용: ");
        String newContent = sc.next();

        Post post = new Post(newTitle, newContent);
        post.setId(nextId++);
        posts.add(post);
        System.out.println(nextId -1 + "번 게시물이 생생되었습니다.");
    }

    @Override
    public Post read(int id) {
        if (posts.isEmpty()) {
            System.out.println("게시판이 비어있습니다. 우선 게시물을 작성해주세요.");
            return null;
        }
        if (!this.contains(id)) {
            return null;
        }
        System.out.println(id + "번 게시물");
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public void update(int id) {
        if (posts.isEmpty()) {
            System.out.println("게시판이 비어있습니다. 우선 게시물을 작성해주세요.");
            return;
        }
        if (!this.contains(id)) {
            return;
        }
        for (Post post : posts) {
            if (post.getId() == id) {
                Scanner sc = new Scanner(System.in);
                System.out.println("제목: ");
                String newTitle = sc.next();
                System.out.println("내용: ");
                String newContent = sc.next();
                post.setTitle(newTitle);
                post.setContent(newContent);
                System.out.println(id + "번 게시물이 성공적으로 수정되었습니다.");
                return;
            }
        }


    }

    @Override
    public void delete(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                System.out.println(id + "번 게시물이 성공적으로 삭제되었습니다.");
                return;
            }
        }
        System.out.println(id + "번 게시물은 존재하지 않습니다.");
    }

    @Override
    public void traverse() {
        if (posts.isEmpty()) {
            System.out.println("게시판이 비어있습니다. 우선 게시물을 작성해주세요.");
            return;
        }
        System.out.println("총 게시글은 " + posts.size() + "개 작성되었습니다.");
        for (Post post : posts) {
            System.out.println(post.getId() + "번 게시글");
            System.out.println("제목: " + post.getTitle());
            System.out.println("내용: " + post.getContent());
            System.out.println();
        }
    }

    @SuppressWarnings("all")
    public boolean contains(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return true;
            }
        }
        System.out.println(id + "번 게시물은 존재하지 않습니다.");
        return false;
    }


}
