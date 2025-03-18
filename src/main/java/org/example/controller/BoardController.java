package org.example.controller;

import org.example.request.Request;
import org.example.service.BoardService;

public class BoardController implements Controller{
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public void handleRequest(Request request) {
        switch (request.getFunction()) {
            case "add":
                boardService.add(request);
                break;
            case "remove":
                boardService.remove(request);
                break;
            case "edit":
                boardService.edit(request);
                break;
            case "view":
                boardService.view(request);
                break;
            default:
                System.out.println("존재하지 않는 기능입니다.");
        }
    }
}
