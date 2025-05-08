package com.banking.menus;

import com.banking.utils.ReadOption;
import com.banking.commands.CreditCardCredentials;
import com.banking.commands.ShowLastMessages;
import com.banking.menus.ui.StartMenuView;
import com.banking.service.UserService;

import java.util.Map;
import java.util.Scanner;


public class StartMenu implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ReadOption readOption = new ReadOption();
    private final StartMenuView showMainMenu = new StartMenuView();
    private final Map<Integer, Menu> commands;
    private final UserService userService = new UserService();
    private boolean running = true;

    public StartMenu() {
        this.commands = Map.of(
                1, () -> new LoginMenu(userService, scanner).show(),
                2, new ShowLastMessages(),
                3, new CreditCardCredentials(),
                4, this::exit
        );
    }

    @Override
    public void show() {
        while (running) {
//            ConsoleClearUtility.clear();
            showMainMenu.showMenu();
            int opt = readOption.readOption(4);
            commands.getOrDefault(opt, () -> System.out.println("Invalid option")).show();
        }
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }


}
