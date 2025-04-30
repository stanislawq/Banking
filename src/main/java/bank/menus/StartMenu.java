package bank.menus;

import bank.ReadOption;
import bank.commands.*;
import bank.menus.show.ShowMainMenu;
import bank.service.UserService;

import java.util.Map;
import java.util.Scanner;


public class StartMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final ReadOption readOption = new ReadOption();
    private final ShowMainMenu showMainMenu = new ShowMainMenu();
    private final Map<Integer, MenuCommand> commands;
    private final UserService userService = new UserService();
    private boolean running = true;

    public StartMenu() {
        commands = Map.of(
                1, () -> new LoginMenu(userService, scanner).execute(),
                2, new ShowLastMessagesCommand(),
                3, new CreditCardCredentialsCommand(),
                4, new ExitCommand()
        );
    }

    public void run() {
        while (running) {
//            ConsoleClearUtility.clear();
            showMainMenu.showMenu();
            int opt = readOption.readOption(4);
            commands.getOrDefault(opt, () -> System.out.println("Invalid option")).execute();
        }
    }


}
