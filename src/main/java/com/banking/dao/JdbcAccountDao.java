package com.banking.dao;

import com.banking.dto.AccountDto;
import com.banking.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao {

    private static final String SQL_SELECT = """
        SELECT currency, balance
        FROM accounts
        WHERE username = ?
    """;

    @Override
    public List<AccountDto> findByUsername(String username) {
        List<AccountDto> list = new ArrayList<>();
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_SELECT)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountDto(
                        rs.getString("currency"),
                        rs.getBigDecimal("balance")));
            }
        } catch (Exception e) {
            throw new RuntimeException("DB error", e);
        }
        return list;
    }


    private static final String SQL_INSERT = """
        INSERT INTO accounts (username, currency)
        VALUES (?, ?)
    """;

    @Override
    public void openAccount(String username, String currencyCode) {
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(SQL_INSERT)) {

            ps.setString(1, username);
            ps.setString(2, currencyCode);
            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException dup) {
            throw new IllegalStateException("Account already exists in " + currencyCode);
        } catch (Exception e) {
            throw new RuntimeException("DB error", e);
        }
    }
}
