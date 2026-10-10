package com.example.taskmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :start AND :end " +
           "AND (:completed IS NULL OR t.completed = :completed)")
    List<Task> findByPeriodAndCompleted(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("completed") Boolean completed);
}