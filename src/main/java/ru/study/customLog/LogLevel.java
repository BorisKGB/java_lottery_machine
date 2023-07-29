package ru.study.customLog;

public enum LogLevel {
    INFO("info"),
    WARN("warning");

    private final String name;
    LogLevel(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
    public static LogLevel fromString(String name) {
        for (LogLevel level:LogLevel.values()) {
            if (name.equals(level.name)) return level;
        }
        return null;
    }
}
