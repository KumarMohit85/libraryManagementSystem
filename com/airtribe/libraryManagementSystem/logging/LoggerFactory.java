package com.airtribe.libraryManagementSystem.logging;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return new ConsoleLogger(clazz.getSimpleName());
    }
}
