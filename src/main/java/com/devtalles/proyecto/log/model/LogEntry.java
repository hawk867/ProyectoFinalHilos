package com.devtalles.proyecto.log.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {
    private LocalDateTime timestamp;
    private String user;
    private String action;
    private int statusCode;
    private int responseTimeMs;
}
