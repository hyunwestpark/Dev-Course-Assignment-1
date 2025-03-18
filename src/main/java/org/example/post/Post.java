package org.example.post;

import org.example.account.Account;
import org.example.board.Board;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private Board board;
    private Account creator;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Post(String title, String content, Board board, Account creator) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.board = board;
        this.creator = creator;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
