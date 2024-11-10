package menus;

import tasks.Task;
import tasks.TaskFactory;
import tasks.TaskRepository;
import taskStrategy.DeadlineStrategy;
import taskStrategy.NoDeadlineStrategy;
import taskStrategy.TaskStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final TaskRepository repository = TaskRepository.getInstance();

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        TaskStrategy deadlineStrategy = new DeadlineStrategy();
        deadlineStrategy.sort(repository.getTasks()); // Mostra tarefas para os próximos 7 dias

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
                case 1 -> addTask(sc);
                case 2 -> viewAllTasks();
                case 3 -> updateTask(sc);
                case 4 -> deleteTask(sc);
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addTask(Scanner sc) {
        System.out.print("Task name: ");
        String name = sc.nextLine();
        System.out.print("Task description: ");
        String description = sc.nextLine();
        System.out.print("Do you want to set a deadline? (y/n): ");
        String hasDeadline = sc.nextLine();

        Task task;
        if (hasDeadline.equalsIgnoreCase("y")) {
            System.out.print("Enter deadline (YYYY-MM-DD): ");
            LocalDate deadline = LocalDate.parse(sc.nextLine());
            task = TaskFactory.createTask(name, description, deadline);
        } else {
            task = TaskFactory.createTask(name, description, null);
        }

        repository.addTask(task);
        System.out.println("Task added successfully!");
    }

    private static void viewAllTasks() {
        TaskStrategy noDeadlineStrategy = new NoDeadlineStrategy();
        noDeadlineStrategy.sort(repository.getTasks());
    }

    private static void updateTask(Scanner sc) {
        System.out.print("Enter the task name to update: ");
        String name = sc.nextLine();
        Task oldTask = repository.getTasks().stream()
                .filter(task -> task.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (oldTask != null) {
            System.out.print("New task name: ");
            String newName = sc.nextLine();
            System.out.print("New description: ");
            String newDescription = sc.nextLine();
            System.out.print("New deadline (YYYY-MM-DD or leave empty): ");
            String newDeadlineStr = sc.nextLine();

            LocalDate newDeadline = newDeadlineStr.isEmpty() ? null : LocalDate.parse(newDeadlineStr);
            Task newTask = TaskFactory.createTask(newName, newDescription, newDeadline);
            repository.updateTask(oldTask, newTask);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteTask(Scanner sc) {
        System.out.print("Enter the task name to delete: ");
        String name = sc.nextLine();
        repository.getTasks().removeIf(task -> task.getName().equalsIgnoreCase(name));
        System.out.println("Task deleted successfully!");
    }
}
