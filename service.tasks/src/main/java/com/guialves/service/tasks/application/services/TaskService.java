package com.guialves.service.tasks.application.services;

import com.guialves.service.tasks.application.dtos.NotificationRequest;
import com.guialves.service.tasks.core.entities.TasksEntity;
import com.guialves.service.tasks.core.repositories.TasksRepository;
import com.guialves.service.tasks.core.ports.NotificationPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TasksRepository tasksRepository;
    private final NotificationPort notificationPort;

    public TaskService(TasksRepository tasksRepository, NotificationPort notificationPort) {
        this.tasksRepository = tasksRepository;
        this.notificationPort = notificationPort;
    }

    public void sendNotificationForDueTasks() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(1);
        List<TasksEntity> tasks = tasksRepository.findTasksDueWithinDeadline(deadline);
        for (TasksEntity task : tasks) {
            NotificationRequest request = new NotificationRequest(
                    "Sua tarefa: " + task.getTitle() + " está prestes a vencer",
                    task.getEmail()
            );

            notificationPort.sendNotification(request);

            task.setNotified(true);
            tasksRepository.save(task);
        }
    }
}