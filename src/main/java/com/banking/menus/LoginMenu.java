package com.banking.menus;

import com.banking.utils.ReadOption;
import com.banking.commands.Login;
import com.banking.commands.Register;
import com.banking.menus.ui.LoginMenuView;
import com.banking.service.UserService;

import java.util.Map;
import java.util.Scanner;

public class LoginMenu implements Menu {
    private final ReadOption readOption = new ReadOption();
    private final LoginMenuView showLoginMenu = new LoginMenuView();
    private final Map<Integer, Menu> commands;
    private boolean running = true;


    public LoginMenu(UserService userService, Scanner scanner) {
        this.commands = Map.of(
                1, new Login(userService, scanner),
                2, new Register(userService, scanner),
                3, () -> running = false
        );
    }

    @Override
    public void show() {
        while (running) {
//            ConsoleClearUtility.clear();
            showLoginMenu.showMenu();
            int opt = readOption.readOption(3);
            commands.getOrDefault(opt, () -> System.err.println("Invalid option")).show();
        }

    }
}

