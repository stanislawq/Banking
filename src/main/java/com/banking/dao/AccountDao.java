package com.banking.dao;

import com.banking.dto.AccountDto;
import java.util.List;

public interface AccountDao {
    List<AccountDto> findByUsername(String username);

    void openAccount(String username, String currencyCode);
}