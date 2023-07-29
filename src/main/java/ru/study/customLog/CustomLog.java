package ru.study.customLog;

import java.time.LocalTime;

public class CustomLog {
    private static CustomLog instance = null;
    private LocalTime time;
    private CustomLog() {
        this.time = LocalTime.parse("09:00");
    }
    public static CustomLog getInstance() {
        if (instance == null) {
            instance = new CustomLog();
        }
        return instance;
    }

    public void log(LogLevel level, String message) {
        // some log action
    }

    public void info(String message) {log(LogLevel.INFO, message);}
    public void warn(String message) {log(LogLevel.WARN, message);}

    public void setTime(int hour, int minute) {
        this.time = LocalTime.parse(String.format("%d:%d", hour, minute));
    }
    public int getHour() {
        return time.getHour();
    }
    public int getMinute() {
        return time.getMinute();
    }
    public void addMinutes(int minute) {
        this.time = time.plusMinutes(minute);
    }
    public void addHours(int hour) {
        this.time = time.plusHours(hour);
    }

}
