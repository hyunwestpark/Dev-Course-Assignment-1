package org.example.session;

import org.example.account.Account;

public class Session {
    private Account loggedInAccount;

    public boolean isLoggedIn() {
        System.out.println("Session.isLoggedIn");
        System.out.println("loggedInAccount = " + loggedInAccount);
        return loggedInAccount != null;
    }

    public Account getLoggedInAccount() {
        System.out.println("Session.getLoggedInAccount");
        System.out.println("loggedInAccount = " + loggedInAccount);
        return loggedInAccount;
    }

    public void login(Account account) {
        if (isLoggedIn()) {
            throw new RuntimeException("이미 로그인 되어 있습니다. ");
        }
        System.out.println("Session.login");
        System.out.println("account = " + account);
        this.loggedInAccount = account;
        System.out.println("loggedInAccount = " + loggedInAccount);
    }

    public void logout() {
        if (!isLoggedIn()) {
            throw new RuntimeException("로그인 상태가 아닙니다.");
        }
        this.loggedInAccount = null;
    }
}
