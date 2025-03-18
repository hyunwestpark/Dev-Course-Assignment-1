package org.example.controller;

import org.example.request.Request;
import org.example.service.AccountService;

public class AccountController implements Controller{
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handleRequest(Request request) {
        switch (request.getFunction()) {
            case "signup":
                accountService.signup(request);
                break;
            case "signin":
                accountService.signin(request);
                break;
            case "signout":
                accountService.signout(request);
                break;
            case "detail":
                accountService.detail(request);
                break;
            case "edit":
                accountService.edit(request);
                break;
            case "remove":
                accountService.remove(request);
                break;
            default:
                System.out.println("존재하지 않는 기능입니다.");
        }
    }


}
