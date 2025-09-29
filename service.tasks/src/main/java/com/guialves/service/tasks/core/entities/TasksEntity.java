package com.guialves.service.tasks.core.entities;

import com.guialves.service.tasks.application.dtos.TaskRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Entity(name = "Task")
@Table(name = "task")
@Data
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime dueDate;
    private Boolean notified;
    private String email;

    protected TasksEntity() {}

    public TasksEntity(TaskRequest taskRequest) {
        this.title = taskRequest.title();
        this.email = taskRequest.email();
        this.dueDate = taskRequest.dueDate();
        this.notified = taskRequest.notified();
    }
}

