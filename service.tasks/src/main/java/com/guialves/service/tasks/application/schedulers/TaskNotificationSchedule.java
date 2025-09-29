package com.guialves.service.tasks.application.schedulers;

import com.guialves.service.tasks.application.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskNotificationSchedule {
    private final TaskService taskService;

    @Autowired
    public TaskNotificationSchedule(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(fixedRate = 120 )
    public void checkAndNotifyTasks() { this.taskService.sendNotificationForDueTasks();}
}
