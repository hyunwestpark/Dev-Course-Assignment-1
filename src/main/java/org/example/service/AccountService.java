package org.example.service;

import org.example.account.Account;
import org.example.request.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountService {
    private Map<Integer, Account> accounts;
    private int nextId;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AccountService() {
        this.accounts = new HashMap<>();
        this.nextId = 1;
    }

    public void signup(Request request) {
        Scanner sc = new Scanner(System.in);

        System.out.print("계정명: ");
        String username = sc.nextLine();

        for (Account account : accounts.values()) {
            if (account.getUsername().equals(username)) {
                System.out.println("이미 존재하는 계정명입니다.");
                return;
            }
        }

        System.out.print("비밀번호: ");
        String password = sc.nextLine();
        System.out.print("닉네임: ");
        String nickname = sc.nextLine();
        System.out.print("이메일: ");
        String email = sc.nextLine();

        Account account = new Account(username, password, nickname, email);
        account.setId(nextId++);
        accounts.put(account.getId(), account);

        System.out.println("회원가입이 완료되었습니다.");
    }

    public void signin(Request request) {
        if (request.getSession().isLoggedIn()) {
            System.out.println("이미 로그인 되어있습니다. 로그아웃 후 다시 시도해주세요.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("계정명: ");
        String username = sc.nextLine();
        System.out.print("비밀번호: ");
        String password = sc.nextLine();

        Account account = null;
        for (Account a : accounts.values()) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                account = a;
                break;
            }
        }

        if (account == null) {
            System.out.println("계정명 또는 비밀번호가 일치하지 않습니다.");
            return;
        }

        request.getSession().login(account);
        System.out.println(account.getNickname() + "님, 환영합니다!");
    }

    public void signout(Request request) {
        if (!request.getSession().isLoggedIn()) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        Account account = request.getSession().getLoggedInAccount();
        request.getSession().logout();
        System.out.println(account.getNickname() + "님, 로그아웃 되었습니다.");
    }

    public void detail(Request request) {
        String accountIdStr = request.getParameter("accountId");
        if (accountIdStr == null) {
            System.out.println("회원 ID를 입력해주세요.");
            return;
        }

        int accountId;
        try {
            accountId = Integer.parseInt(accountIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 회원 ID를 입력해주세요.");
            return;
        }

        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println(accountId + "번 회원은 존재하지 않습니다.");
            return;
        }

        System.out.println("[" + accountId + "]번 회원");
        System.out.println("계정: " + account.getUsername());
        System.out.println("닉네임: " + account.getNickname());
        System.out.println("이메일: " + account.getEmail());
        System.out.println("가입일: " + account.getCreatedAt().format(formatter));
        if (!account.getCreatedAt().equals(account.getUpdatedAt())) {
            System.out.println("정보 수정일: " + account.getUpdatedAt().format(formatter));
        }
    }

    public void edit(Request request) {
        String accountIdStr = request.getParameter("accountId");
        if (accountIdStr == null) {
            System.out.println("회원 ID를 입력해주세요.");
            return;
        }

        int accountId;
        try {
            accountId = Integer.parseInt(accountIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 회원 ID를 입력해주세요.");
            return;
        }

        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println(accountId + "번 회원은 존재하지 않습니다.");
            return;
        }

        if (!request.getSession().isLoggedIn() ||
                request.getSession().getLoggedInAccount().getId() != accountId) {
            System.out.println("본인 계정만 수정할 수 있습니다.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("새 비밀번호(변경하지 않으려면 엔터): ");
        String password = sc.nextLine();

        System.out.print("새 이메일(변경하지 않으려면 엔터): ");
        String email = sc.nextLine();

        if (!password.isEmpty()) {
            account.setPassword(password);
        }

        if (!email.isEmpty()) {
            account.setEmail(email);
        }

        account.setUpdatedAt(LocalDateTime.now());
        System.out.println("계정 정보가 성공적으로 수정되었습니다.");
    }

    public void remove(Request request) {
        String accountIdStr = request.getParameter("accountId");
        if (accountIdStr == null) {
            System.out.println("회원 ID를 입력해주세요.");
            return;
        }

        int accountId;
        try {
            accountId = Integer.parseInt(accountIdStr);
        } catch (NumberFormatException e) {
            System.out.println("유효한 회원 ID를 입력해주세요.");
            return;
        }

        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println(accountId + "번 회원은 존재하지 않습니다.");
            return;
        }

        if (!request.getSession().isLoggedIn() ||
                request.getSession().getLoggedInAccount().getId() != accountId) {
            System.out.println("본인 계정만 탈퇴할 수 있습니다.");
            return;
        }

        if (request.getSession().isLoggedIn() &&
                request.getSession().getLoggedInAccount().getId() == accountId) {
            request.getSession().logout();
        }

        accounts.remove(accountId);
        System.out.println(accountId + "번 회원이 성공적으로 탈퇴되었습니다.");
    }

    public Account getAccountById(int id) {
        return accounts.get(id);
    }
}