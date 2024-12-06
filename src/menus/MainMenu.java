package menus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add new task");
            System.out.println("2. View all tasks");
            System.out.println("3. Update a task");
            System.out.println("4. Delete a task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


}
