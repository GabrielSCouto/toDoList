package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tasks {
    private String taskName;
    private String taskDescription;
    private String taskDate;

    static List<Tasks> tasks = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public Tasks(){

    }

    public Tasks(String taskName, String taskDescription, String taskDate) {
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

    public String getTaskDate() {
        return taskDate;
    }

    public static void addTask(){

        System.out.println("Enter task data:");
        System.out.print("TITLE: ");
        String taskName = sc.nextLine();
        System.out.print("DESCRIPTION: ");
        String taskDescription = sc.nextLine();
        System.out.print("Wish to add deadline to current task? (y/n) ");
        String option = sc.nextLine().trim().toLowerCase();

        do {
            switch (option){
                case "y":
                    System.out.println("DEADLINE (yyyy-mm-dd): ");
                    String taskDate = sc.nextLine();
                    tasks.add(new Tasks(taskName, taskDescription, taskDate));
                    break;
                case "n":
                    tasks.add(new Tasks(taskName, taskDescription));
                    break;
                default:
                    System.out.println("Invalid entry, try again!");
                    break;
            }
        } while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n"));

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
