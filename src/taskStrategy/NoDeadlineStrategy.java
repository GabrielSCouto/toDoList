package taskStrategy;

import tasks.Task;
import java.util.List;

public class NoDeadlineStrategy implements TaskStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort((task1, task2) -> {
            if (task1.getDeadline() == null && task2.getDeadline() != null) return -1;
            if (task1.getDeadline() != null && task2.getDeadline() == null) return 1;
            if (task1.getDeadline() == null && task2.getDeadline() == null) {
                return task1.getName().compareToIgnoreCase(task2.getName());
            }
            return task1.getDeadline().compareTo(task2.getDeadline());
        });

        System.out.println("\n--- Todas as tarefas ---");
        tasks.forEach(System.out::println);
    }
}
