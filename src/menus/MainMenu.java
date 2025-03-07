package menus;

import entities.Tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("|-----------------------|");
        System.out.println("|NEXT 7 SEVEN DAYS TASKS|");
        System.out.println("|-----------------------|");

        //display here the next week tasks


        while (true) {
            System.out.println("\n------------------");
            System.out.println("1. Add new task");
            System.out.println("2. View all tasks");
            System.out.println("3. Update a task");
            System.out.println("4. Delete a task");
            System.out.println("5. Reset file");
            System.out.println("0. Exit");
            System.out.println("------------------");
            System.out.println();
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Tasks.addTask();
                    break;
                case 2:
                    Tasks.showTasks();
                    break;
                case 3:
                    Tasks.updateTask();
                    break;
                case 4:
                    Tasks.removeTask();
                    break;
                case 5:
                    Tasks.resetFile();
                case 0:
                    System.out.println("Exiting...");
                    return;

                default: System.out.println("Invalid option. Try again.");
            }
        }
    }


}
