package tasks;

import java.time.LocalDate;

public class Task {
    private String name;
    private String description;
    private LocalDate deadline;

    // Constructor with deadline
    public Task(String name, String description, LocalDate deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    // Constructor without deadline
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task: " + name + ", Description: " + description +
                (deadline != null ? ", Deadline: " + deadline : ", No Deadline");
    }
}
