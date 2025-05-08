package com.banking.menus.ui;

public class LoginMenuView implements MenuView {
    public void showMenu() {
        System.out.println("""
                
                --------------------------------------
                │               Login                │
                --------------------------------------
                │1. Login to existing account        │
                │2. Register new user                │
                │3. Go back to Main Page             │
                --------------------------------------
                Choose an option:\s""");
    }
}
