package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tasks {
    private String taskName;
    private String taskDescription;
    private LocalDate taskDate;

    static List<Tasks> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    private static final String TASKS_FILE = "tasks.csv";

    public Tasks(){

    }

    public Tasks(String taskName, String taskDescription, LocalDate taskDate) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    public Tasks(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public static void addTask() {

        System.out.println("Enter task data:");
        System.out.print("TITLE: ");
        String taskName = sc.nextLine();
        System.out.print("DESCRIPTION: ");
        String taskDescription = sc.nextLine();

        String option;
        do {
            System.out.print("Wish to add deadline to current task? (y/n) ");
            option = sc.nextLine().trim().toLowerCase();
            switch (option) {
                case "y":
                    System.out.println("DEADLINE (yyyy-mm-dd): ");
                    String taskDate = sc.nextLine();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    tasks.add(new Tasks(taskName, taskDescription, LocalDate.parse(taskDate, format)));
                    break;
                case "n":
                    tasks.add(new Tasks(taskName, taskDescription));
                    break;
                default:
                    System.out.println("Invalid entry, try again!");
                    break;
            }
        } while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FILE))) {
            for (Tasks task : tasks){
                writer.write(String.format("%s/%s/%s\n", task.getTaskName(), task.getTaskDescription(), task.getTaskDate()));
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks" + e.getMessage());
        }
    }

    public static void removeTask(){
        showTasks();

        System.out.println("\nEnter task name to delete it: ");
        String nameDel = sc.nextLine();

        tasks.removeIf(x -> x.getTaskName().equalsIgnoreCase(nameDel));

        System.out.println("Task deleted successfully! Modified list: ");
        System.out.println();

        showTasks();
    }

    public static void updateTask(){
        showTasks();
        System.out.println("\nEnter task name to update: ");
        String nameUpd = sc.nextLine();

        Tasks result = tasks.stream().filter(x -> x.getTaskName().equalsIgnoreCase(nameUpd)).findFirst().orElse(null);

        if (result != null) {
            System.out.println("What do you wish to update?");
            System.out.println("\n1 - All info\n2 - Title\n3 - Description\n4 - Deadline");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.print("\nUpdating all info... ");
                    System.out.print("New title: ");
                    result.taskName = sc.nextLine();
                    System.out.print("New description: ");
                    result.taskDescription = sc.nextLine();
                    if (result.taskDate != null) {
                        System.out.print("New deadline: ");
                        result.taskDate =  LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                    } else {
                        System.out.print("Wish to add a deadline to this task? (y/n) ");
                        String option1 = sc.nextLine();

                        do {
                            switch (option1){
                                case "y":
                                    System.out.print("New deadline (yyyy-mm-dd): ");
                                    result.taskDate =  LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                                    break;
                                case "n":
                                    break;
                                default:
                                    System.out.println("Invalid entry, try again!");
                                    break;
                            }
                        } while (!option1.equalsIgnoreCase("y") && !option1.equalsIgnoreCase("n"));
                    }
                    break;
                case "2":
                    System.out.print("New title: ");
                    result.taskName = sc.nextLine();
                    break;
                case "3":
                    System.out.print("New description: ");
                    result.taskDescription = sc.nextLine();
                    break;
                case "4":
                    System.out.print("New deadline: ");
                    result.taskDate =  LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    break;
                default:
                    break;
            }

        } else {
            System.out.println("Task not found!");
        }
    }

    public static void showTasks(){
        System.out.println("Showing tasks: ");
        for (Tasks task : tasks) {
            System.out.println(task);
        }
    }

    public String toString(){
        return "Title: " + taskName + ", Description: " + taskDescription + ", Deadline: " + taskDate;
    }
}
