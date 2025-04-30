package bank.commands;

public class ExitCommand implements MenuCommand {
    @Override
    public void execute() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
