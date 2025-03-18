package org.example.controller;

import org.example.request.Request;
import org.example.service.BoardService;
import org.example.service.PostService;

public class PostController implements Controller{
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void handleRequest(Request request) {
        switch (request.getFunction()) {
            case "add":
                postService.add(request);
                break;
            case "remove":
                postService.remove(request);
                break;
            case "edit":
                postService.edit(request);
                break;
            case "view":
                postService.view(request);
                break;
            default:
                System.out.println("존재하지 않는 기능입니다.");
        }
    }
}
