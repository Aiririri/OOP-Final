package university.ui;

public class AdminMenu {
    public void show() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  ADMIN MENU");
            System.out.println("========================================");
            System.out.println("  1. View users (not implemented)");
            System.out.println("  0. Logout");

            int choice = Input.number("Choice", 0, 1);
            switch (choice) {
                case 1 -> System.out.println("  Not implemented yet.");
                case 0 -> { System.out.println("  Logged out."); return; }
            }
        }
    }
}
