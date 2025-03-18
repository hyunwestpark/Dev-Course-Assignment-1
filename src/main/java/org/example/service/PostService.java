package org.example.service;

import org.example.account.Account;
import org.example.board.Board;
import org.example.post.Post;
import org.example.request.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PostService {
    private Map<Integer, Post> posts;
    private int nextId;
    private BoardService boardService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PostService(BoardService boardService) {
        this.posts = new HashMap<>();
        this.nextId = 1;
        this.boardService = boardService;
    }

    public void add(Request request) {
        String boardIdStr = request.getParameter("boardId");
        if (boardIdStr == null) {
            System.out.println("게시판 ID를 입력해주세요. ");
            return;
        }

        int boardId;

        try {
            boardId = Integer.parseInt(boardIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시판 ID를 입력해주세요.");
            return;
        }

        Board board = boardService.getBoardById(boardId);

        if (board == null) {
            System.out.println(boardId + "번 게시판은 존재하지 않습니다.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        Account author = null;
        if (request.getSession().isLoggedIn()) {
            author = request.getSession().getLoggedInAccount();
        }

        Post post = new Post(title, content, board, author);
        post.setId(nextId++);
        posts.put(post.getId(), post);

        System.out.println(post.getId() + "번 게시글이 성공적으로 작성되었습니다.");
    }

    public void remove(Request request) {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            System.out.println("게시글 ID를 입력해주세요.");
            return;
        }

        int postId;
        try {
            postId = Integer.parseInt(postIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시글 ID를 입력해주세요.");
            return;
        }

        Post post = posts.get(postId);
        if (post == null) {
            System.out.println(postId + "번 게시글은 존재하지 않습니다.");
            return;
        }

        posts.remove(postId);
        System.out.println(postId + "번 게시글이 성공적으로 삭제되었습니다!");
    }


    public void edit(Request request) {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            System.out.println("게시글 ID를 입력해주세요.");
            return;
        }

        int postId;
        try {
            postId = Integer.parseInt(postIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시글 ID를 입력해주세요.");
            return;
        }

        Post post = posts.get(postId);
        if (post == null) {
            System.out.println(postId + "번 게시글은 존재하지 않습니다.");
            return;
        }

        System.out.println(postId + "번 게시글을 수정합니다.");

        Scanner sc = new Scanner(System.in);
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());

        System.out.println(postId + "번 게시글이 성공적으로 수정되었습니다!");
    }

    public List<Post> getPostsByBoard(Board board) {
    List<Post> boardPosts = new ArrayList<>();
    for (Post post : posts.values()) {
        if (post.getBoard().equals(board)) {
            boardPosts.add(post);
        }
    }
    return boardPosts;
}

    public void view(Request request) {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            System.out.println("게시글 ID를 입력해주세요.");
            return;
        }

        int postId;
        try {
            postId = Integer.parseInt(postIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시글 ID를 입력해주세요.");
            return;
        }

        Post post = posts.get(postId);
        if (post == null) {
            System.out.println(postId + "번 게시글은 존재하지 않습니다.");
            return;
        }

        System.out.println("[" + postId + "]번 게시글");
        System.out.println("작성일: " + post.getCreatedAt().format(formatter));
        System.out.println("수정일: " + post.getUpdatedAt().format(formatter));
        System.out.println("게시판: " + post.getBoard().getName());
        System.out.println("작성자: " + (post.getCreator() != null ? post.getCreator().getNickname() : "비회원"));
        System.out.println("제목: " + post.getTitle());
        System.out.println("내용: " + post.getContent());
    }
}