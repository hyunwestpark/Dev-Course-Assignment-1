package org.example.session;

import org.example.account.Account;

public class Session {
    private Account loggedInAccount;

    public boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void login(Account account) {
        if (isLoggedIn()) {
            throw new RuntimeException("이미 로그인 되어 있습니다. ");
        }
        this.loggedInAccount = account;
    }

    public void logout() {
        if (!isLoggedIn()) {
            throw new RuntimeException("로그인 상태가 아닙니다.");
        }
        this.loggedInAccount = null;
    }
}
