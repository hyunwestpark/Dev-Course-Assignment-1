package org.example;

import org.example.controller.AccountController;
import org.example.controller.BoardController;
import org.example.controller.PostController;
import org.example.request.Request;
import org.example.request.RequestParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RequestParser parser = new RequestParser();

        AccountController accountController = new AccountController();
        BoardController boardController = new BoardController();
        PostController postController = new PostController();
        while (true) {
            System.out.println("a > ");
            String url = sc.nextLine();

            if (url.equals("exit")) {
                System.out.println("프로그램이 종료됩니다.");
                break;
            }

            try {
                Request request = parser.parse(url);

                switch (request.getRoute()) {
                    case "boards":
                        boardController.handleRequest(request);
                        break;
                    case "posts":
                        postController.handleRequest(request);
                        break;
                    case "accounts":
                        accountController.handleRequest(request);
                        break;
                    default:
                        System.out.println(request.getRoute());
                        System.out.println("존재하지 않는 경로입니다.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

           }
    }
}