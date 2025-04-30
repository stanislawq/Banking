package bank.commands;

import bank.menus.MainUserMenu;
import bank.service.UserService;

import java.util.Scanner;

public class LoginCommand implements MenuCommand {
    private final Scanner INPUT;
    private final UserService userService;

    public LoginCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.INPUT = scanner;
    }


    @Override
    public void execute() {
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
                        new MainUserMenu().execute();
                        return;
                    }
                    System.out.println("Wrong password!");
                }
            } else System.out.println("Username is incorrect!\n");
        }


    }


}
