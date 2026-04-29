package university.ui;

import java.util.Scanner;

public class Input {
    private static final Scanner SC = new Scanner(System.in);

    public static String line(String prompt) {
        System.out.print("  " + prompt + ": ");
        return SC.nextLine().trim();
    }

    public static int number(String prompt, int min, int max) {
        while (true) {
            System.out.print("  " + prompt + " [" + min + "-" + max + "]: ");
            try {
                int v = Integer.parseInt(SC.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("  Please enter a number between " + min + " and " + max + ".");
        }
    }
}
