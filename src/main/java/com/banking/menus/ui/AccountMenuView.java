package com.banking.menus.ui;

public class AccountMenuView implements MenuView {
    @Override
    public void showMenu() {
        System.out.println("""
                
                --------------------------------------
                │                BANK                │
                --------------------------------------
                │ 1. Main Page                       │
                │ 2. Payments                        │
                │ 3. My Products                     │
                │ 4. Offers                          │
                │ 5. More...                         │
                | 6. Go back to Main Menu            │
                --------------------------------------
                Choose an option:\s""");
    }
}


