package com.banking.service;

import com.banking.dao.AccountDao;
import com.banking.dto.AccountDto;

import java.util.List;

public class AccountService {
    private final AccountDao dao;

    public AccountService(AccountDao dao) {
        this.dao = dao;
    }

    public List<AccountDto> listAccounts(String username) {
        return dao.findByUsername(username);
    }

    public void createAccount(String username, String currency) {
        dao.openAccount(username, currency);
    }
}
