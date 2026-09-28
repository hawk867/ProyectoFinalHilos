package com.devtalles.proyecto.log.service;

import com.devtalles.proyecto.log.model.LogEntry;
import com.devtalles.proyecto.log.model.LogSummary;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class LogProcessorTask implements Callable<LogSummary> {

    private final List<LogEntry> logEntries;

    public LogProcessorTask(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    @Override
    public LogSummary call() throws Exception {

        List<LogEntry> errorLogs = logEntries
                .stream()
                .filter(e -> e.getStatusCode() >= 400)
                .toList();

        int totalEntries = logEntries.size();
        int errorCount = errorLogs.size();

        Set<String> uniqueUsers = logEntries
                .stream()
                .map(LogEntry::getUser)
                .collect(Collectors.toSet());

        /*
        double averageResponseTime = logEntries.stream()
                .map(LogEntry::getResponseTimeMs)
                .collect(Collectors.averagingInt(Integer::intValue));
         **/

        double averageResponseTime = logEntries.stream()
                .mapToInt(LogEntry::getResponseTimeMs)
                .average()
                .orElse(0.0);

        Map<Integer, Long> errorCountByCode = errorLogs
                .stream()
                .collect(Collectors.groupingBy(LogEntry::getStatusCode, Collectors.counting()));

        System.out.println("Finalizando proceso en el hilo: " + Thread.currentThread().getName());

        return new LogSummary(totalEntries, errorCount, uniqueUsers, averageResponseTime, errorCountByCode);
    }
}
