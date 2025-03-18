package org.example.service;

import org.example.account.Account;
import org.example.board.Board;
import org.example.post.Post;
import org.example.request.Request;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BoardService {
    private Map<Integer, Board> boards;
    private int nextId;
    private PostService postService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BoardService() {
        this.boards = new HashMap<>();
        this.nextId = 1;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public void add(Request request) {
        Scanner sc = new Scanner(System.in);
        System.out.print("게시판 이름: ");
        String name = sc.nextLine();

        Account creator = null;
        if (request.getSession().isLoggedIn()) {
            creator = request.getSession().getLoggedInAccount();
        }

        Board board = new Board(name, creator);
        board.setId(nextId++);
        boards.put(board.getId(), board);

        System.out.println(board.getId() + "번 게시판이 성공적으로 생성되었습니다.");
    }

    public void edit(Request request) {
        String boardIdStr = request.getParameter("boardId");
        if (boardIdStr == null) {
            System.out.println("게시판 ID를 입력해주세요.");
            return;
        }

        int boardId;
        try {
            boardId = Integer.parseInt(boardIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시판 ID를 입력해주세요.");
            return;
        }

        Board board = boards.get(boardId);
        if (board == null) {
            System.out.println(boardId + "번 게시판은 존재하지 않습니다.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("새 게시판 이름: ");
        String name = sc.nextLine();

        board.setName(name);
        System.out.println(boardId + "번 게시판이 성공적으로 수정되었습니다.");
    }

    public void remove(Request request) {
        String boardIdStr = request.getParameter("boardId");
        if (boardIdStr == null) {
            System.out.println("게시판 ID를 입력해주세요.");
            return;
        }

        int boardId;
        try {
            boardId = Integer.parseInt(boardIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 게시판 ID를 입력해주세요.");
            return;
        }

        Board board = boards.get(boardId);
        if (board == null) {
            System.out.println(boardId + "번 게시판은 존재하지 않습니다.");
            return;
        }

        boards.remove(boardId);
        System.out.println(boardId + "번 게시판이 성공적으로 삭제되었습니다.");
    }

    public void view(Request request) {
        String boardName = request.getParameter("boardName");
        if (boardName == null) {
            System.out.println("게시판 이름을 입력해주세요.");
            return;
        }

        Board board = null;
        for (Board b : boards.values()) {
            if (b.getName().equals(boardName)) {
                board = b;
                break;
            }
        }

        if (board == null) {
            System.out.println("'" + boardName + "' 게시판은 존재하지 않습니다.");
            return;
        }

        System.out.println("'" + boardName + "' 게시판 목록");
        System.out.println("------------------------");
        System.out.println("글 번호 / 글 제목 / 작성일");

        List<Post> posts = postService.getPostsByBoard(board);
        if (posts.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (Post post : posts) {
                System.out.println(post.getId() + " / " + post.getTitle() + " / " +
                        post.getCreatedAt().format(formatter));
            }
        }
    }

    public Board getBoardById(int id) {
        return boards.get(id);
    }
}