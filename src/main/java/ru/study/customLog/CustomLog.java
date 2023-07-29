package ru.study.customLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CustomLog {
    private static CustomLog instance = null;
    private LocalTime time;
    private boolean writeToFile;
    private String filePath;
    private FileWriter fileWriter;
    private boolean writeToConsole;

    private CustomLog() {
        this.time = LocalTime.parse("09:00");
        this.writeToFile = false;
        this.filePath = String.join(File.separator, Arrays.asList("data", "app.log"));
        this.fileWriter = null;
        this.writeToConsole = false;
    }
    public static CustomLog getInstance() {
        if (instance == null) {
            instance = new CustomLog();
        }
        return instance;
    }

    public void setWriteToConsole() {
        this.writeToConsole = true;
    }
    public void unsetWriteToConsole() {
        this.writeToConsole = false;
    }

    private void ensureParentDirectories() throws IOException {
        String[] pathElements = Paths.get(this.filePath).getParent().toString().split(Pattern.quote(File.separator));
        Files.createDirectories(Paths.get(String.join(File.separator, pathElements)));
    }
    private void openFile() throws IOException {
        this.fileWriter = new FileWriter(this.filePath, StandardCharsets.UTF_8,true);
    }

    public void setWriteToFile(String filePath) {
        if (!filePath.equals(this.filePath)){
            this.filePath = filePath;
        }
        try {
            ensureParentDirectories();
            this.openFile();
        } catch (IOException e) {
            unsetWriteToFile();
            this.log(LogLevel.WARN, "Unable to log to file, disabling feature");
        }
        this.writeToFile = true;
    }
    public void setWriteToFile() {
        setWriteToFile(this.filePath);
    }
    public void unsetWriteToFile() {
        this.writeToFile = false;
        this.fileWriter = null;
    }

    public void log(LogLevel level, String message) {
        String logMessage = String.format("%s - %s - %s\n", this.time, level.toString(), message);
        if (this.writeToConsole) {
            System.out.print(logMessage);
        }
        if (this.writeToFile) {
            try {
                if (this.fileWriter == null) {
                    this.openFile();
                }
                this.fileWriter.write(logMessage);
                this.fileWriter.flush();
            } catch (IOException e) {
                unsetWriteToFile();
                this.log(LogLevel.WARN, "Unable to log to file, disabling feature");
            }
        }
    }

    public void info(String message) {log(LogLevel.INFO, message);}
    public void warn(String message) {log(LogLevel.WARN, message);}

    public void setTime(int hour, int minute) {
        this.time = LocalTime.parse(String.format("%02d:%d", hour, minute));
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
