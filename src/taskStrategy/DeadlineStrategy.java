package taskStrategy;

import tasks.Task;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DeadlineStrategy implements TaskStrategy {
    @Override
    public void sort(List<Task> tasks) {
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusDays(7);

        List<Task> upcomingTasks = tasks.stream()
                .filter(task -> task.getDeadline() != null &&
                        !task.getDeadline().isBefore(now) &&
                        task.getDeadline().isBefore(nextWeek))
                .sorted((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()))
                .collect(Collectors.toList());

        // Mostrar apenas tarefas para os próximos 7 dias
        System.out.println("\n--- Próximas tarefas para os próximos 7 dias ---");
        if (upcomingTasks.isEmpty()) {
            System.out.println("Nenhuma tarefa para os próximos 7 dias.");
        } else {
            upcomingTasks.forEach(System.out::println);
        }
    }
}
