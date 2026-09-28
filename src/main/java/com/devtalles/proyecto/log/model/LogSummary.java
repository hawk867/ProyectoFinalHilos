package com.devtalles.proyecto.log.model;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class LogSummary {

    private int totalEntries;
    private int errorCount;
    private Set<String> uniqueUsers;
    private double averageResponseTime;
    private Map<Integer, Long> errorCountByCode;

    public String toString() {
        return "LogSummary{" +
                "totalEntries=" + totalEntries +
                ", errorCount=" + errorCount +
                ", uniqueUsers=" + uniqueUsers +
                ", averageResponseTime=" + averageResponseTime +
                ", errorCountByCode=" + errorCountByCode +
                '}';
    }
}
