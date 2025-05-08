package com.banking.menus;

import com.banking.utils.ReadOption;
import com.banking.menus.ui.AccountMenuView;
import com.banking.pages.*;

import java.util.Map;

public class AccountMenu implements Menu {
    private final ReadOption readOption = new ReadOption();
    private final AccountMenuView showMainUserPage = new AccountMenuView();
    private final Map<Integer, Menu> commands;
    private boolean running = true;

    public AccountMenu() {
        this.commands = Map.of(
                1, new MainUserPage(),
                2, new PaymentsUserPage(),
                3, new MyProductsUserPage(),
                4, new OffersUserPage(),
                5, new AdditionalUserPage(),
                6, () -> running = false
        );
    }

    @Override
    public void show() {
        while (running) {
//            ConsoleClearUtility.clear();
            showMainUserPage.showMenu();
            int opt = readOption.readOption(6);
            commands.getOrDefault(opt, () -> System.err.println("Invalid option")).show();
        }

    }
}
