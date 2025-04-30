package bank.menus.show;

public class ShowMainMenu implements ShowMenu {
    @Override
    public void showMenu() {
        System.out.println("""
                
                
                
                --------------------------------------
                │                MENU                │
                --------------------------------------
                │ 1. Login                           │
                │ 2. Show Last Messages              │
                │ 3. Show Credit Card Credentials    │
                | 4. Exit                            │
                --------------------------------------
                Choose an option:\s""");
    }
}
