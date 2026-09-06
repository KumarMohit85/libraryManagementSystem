package com.airtribe.libraryManagementSystem.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger {

    private final String className;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ConsoleLogger(String className) {
        this.className = className;
    }

    private void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + level + "] [" + timestamp + "] [" + className + "] " + message);
    }

    @Override
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    @Override
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
