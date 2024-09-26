package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(("data/log.txt")))) {
            String read;
            while ((read = reader.readLine()) != null) {
                String[] arrayReadLine = read.split(" ");
                if ("404".equals(arrayReadLine[arrayReadLine.length - 2])) {
                    result.add(read);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
