package bank.menus;

import bank.ReadOption;
import bank.commands.MenuCommand;
import bank.menus.pages.*;
import bank.menus.show.ShowMainUserPage;

import java.util.Map;

public class MainUserMenu implements MenuCommand {
    private final ReadOption readOption = new ReadOption();
    private final ShowMainUserPage showMainUserPage = new ShowMainUserPage();
    private final Map<Integer, MenuCommand> commands;
    private boolean running = true;

    public MainUserMenu() {
        commands = Map.of(
                1, new MainUserPage(),
                2, new PaymentsUserPage(),
                3, new MyProductsUserPage(),
                4, new OffersUserPage(),
                5, new AdditionalUserPage(),
                6, () -> running = false
        );
    }

    @Override
    public void execute() {
        while (running) {
//            ConsoleClearUtility.clear();
            showMainUserPage.showMenu();
            int opt = readOption.readOption(6);
            commands.getOrDefault(opt, () -> System.err.println("Invalid option")).execute();
        }

    }
}
