package com.links.login;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String PATH = "log.txt";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String msg) {
        LocalDateTime now = LocalDateTime.now();
        try (FileWriter out = new FileWriter(PATH, true)) {
            out.write("[" + now.format(formatter) + "] " + msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearLogs() {
        try (FileWriter out = new FileWriter(PATH, false)) {
            out.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
