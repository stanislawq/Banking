package com.banking.menus.ui;

public class StartMenuView implements MenuView {
    @Override
    public void showMenu() {
        System.out.println("""

                --------------------------------------
                │                MENU                │
                --------------------------------------
                │ 1. Login                           │
                │ 2. Show Last Messages              │
                │ 3. Show Credit Card Credentials    │
                | 4. Exit                            │
                --------------------------------------
                Choose an option:\s""");
    }
}
