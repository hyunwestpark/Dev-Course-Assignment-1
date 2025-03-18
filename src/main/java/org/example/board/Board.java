package org.example.board;

import org.example.account.Account;

import java.time.LocalDateTime;

public class Board {
    private int id;
    private String name;
    private Account creator;
    private LocalDateTime createdAt;

    public Board(String name, Account creator) {
        this.name = name;
        this.creator = creator;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
