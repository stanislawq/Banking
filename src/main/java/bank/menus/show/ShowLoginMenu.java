package bank.menus.show;

public class ShowLoginMenu implements ShowMenu {
    public void showMenu() {
        System.out.println("""
                
                
                
                --------------------------------------
                │               Login                │
                --------------------------------------
                │1. Login to existing account        │
                │2. Register new user                │
                │3. Go back to Main Page             │
                --------------------------------------
                Choose an option:\s""");
    }
}
