package com.guialves.service.tasks.application.dtos;

import java.time.LocalDateTime;

public record TaskRequest(String title, String email, LocalDateTime dueDate, Boolean notified) {
}
