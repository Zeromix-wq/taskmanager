package com.example.taskmanager;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getTasks(String period, Boolean completed) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.with(LocalTime.MIN);
        LocalDateTime end;

        switch (period.toUpperCase()) {
            case "WEEK" -> end = now.plusWeeks(1).with(LocalTime.MAX);
            case "MONTH" -> end = now.plusMonths(1).with(LocalTime.MAX);
            default -> end = now.with(LocalTime.MAX);
        }
        return repository.findByPeriodAndCompleted(start, end, completed);
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setDueDate(updatedTask.getDueDate());
        return repository.save(existing);
    }

    public Task toggleStatus(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        task.setCompleted(!task.isCompleted());
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}