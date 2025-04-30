package bank.menus;

import bank.ReadOption;
import bank.commands.*;
import bank.menus.show.ShowLoginMenu;
import bank.service.UserService;

import java.util.Map;
import java.util.Scanner;

public class LoginMenu implements MenuCommand {
    private final ReadOption readOption = new ReadOption();
    private final ShowLoginMenu showLoginMenu = new ShowLoginMenu();
    private final Map<Integer, MenuCommand> commands;
    private boolean running = true;


    public LoginMenu(UserService userService, Scanner scanner) {
        commands = Map.of(
                1, new LoginCommand(userService, scanner),
                2, new RegisterCommand(userService, scanner),
                3, () -> running = false
        );
    }

    @Override
    public void execute() {
        while (running) {
//            ConsoleClearUtility.clear();
            showLoginMenu.showMenu();
            int opt = readOption.readOption(3);
            commands.getOrDefault(opt, () -> System.err.println("Invalid option")).execute();
        }

    }
}

