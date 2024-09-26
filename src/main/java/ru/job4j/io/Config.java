package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                if ("#".equals(readLine.split(" ")[0]) || readLine.isEmpty()) {
                    continue;
                }
                if (!checkString(readLine)) {
                    throw new IllegalArgumentException();
                }
                String key = readLine.split("=", 2)[0];
                String value = readLine.split("=", 2)[1];
                values.put(key, value);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return output.toString();
    }

    private boolean checkString(String string) {
        boolean result = true;
        if ("=".equals(string)) {
            result = false;
        }
        if (!string.contains("=")) {
            result = false;
        }
        if ((string.startsWith("=") || string.endsWith("="))
                && string.replaceAll("=", "").length() == string.length() - 1) {
            result = false;
        }
        return result;
    }
}