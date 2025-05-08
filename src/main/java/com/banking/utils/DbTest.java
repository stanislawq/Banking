package com.banking.utils;

public class DbTest {
    public static void main(String[] args) {
        try (var conn = DbUtil.getConnection()) {
            System.out.println("Connected!" +
                    conn.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
