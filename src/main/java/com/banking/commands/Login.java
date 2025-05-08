package com.banking.commands;

import com.banking.menus.AccountMenu;
import com.banking.menus.Menu;
import com.banking.service.UserService;

import java.util.Scanner;

public class Login implements Menu {
    private final Scanner INPUT;
    private final UserService userService;

    public Login(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.INPUT = scanner;
    }


    @Override
    public void show() {
        while (true) {
            System.out.println("Enter username or type \"exit\" to exit: ");
            String username = INPUT.nextLine().trim();
            if (username.equals("exit")) {
                return;
            }
            if (userService.isUserExist(username)) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Enter password, " + "Try n." + (i + 1));
                    String password = INPUT.nextLine().trim();
                    if (userService.checkPassword(username, password)) {
                        System.out.println("Successfully logged in!");
                        new AccountMenu().show();
                        return;
                    }
                    System.out.println("Wrong password!");
                }
            } else System.out.println("Username is incorrect!\n");
        }


    }


}
