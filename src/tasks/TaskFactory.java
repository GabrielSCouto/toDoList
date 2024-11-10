package tasks;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String name, String description, LocalDate deadline) {
        if (deadline != null) {
            return new Task(name, description, deadline);
        } else {
            return new Task(name, description);
        }
    }
}
