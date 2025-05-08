package com.banking.menus.ui;

import com.banking.dao.AccountDao;
import com.banking.dao.JdbcAccountDao;
import com.banking.dto.AccountDto;
import com.banking.service.AccountService;
import java.util.stream.Collectors;

public class MainPageView implements MenuView {
    public static void main(String[] args) {
        AccountDao dao = new JdbcAccountDao();
        AccountService accountService = new AccountService(dao);
        new MainPageView(accountService, "stanislav").showMenu();
    }

    private final AccountService accountService;
    private final String username;

    public MainPageView(AccountService accountService, String username) {
        this.accountService = accountService;
        this.username = username;
    }

    @Override
    public void showMenu() {
        System.out.printf("""
                
                --------------------------------------
                │              Main Page             │
                --------------------------------------
                │1. Available accounts:
                │   %s
                │2. Fast Commands:
                │   %s
                --------------------------------------
                │                News                │
                --------------------------------------
                %s
                Choose an option:\s""", showAvailableAccounts(), showFastCommands(), showNews());
    }

    private String showAvailableAccounts() {
        return accountService.listAccounts(username)
                .stream()
                .map(this::formatAccount)
                .collect(Collectors.joining(", "));
    }

    private String formatAccount(AccountDto a) {
        return "%s: %.2f".formatted(a.currency(), a.balance());
    }

    private String showFastCommands() {
        return "BLIK, Number";
    }

    private String showNews() {
        return "NEWS";
    }
}

