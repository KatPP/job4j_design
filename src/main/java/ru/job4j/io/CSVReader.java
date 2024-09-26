package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        String[] filters = argsName.get("filter").split(",");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String path = argsName.get("path");
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String[] columns = reader.readLine().split(delimiter);
            for (String string : columns) {
                map.put(string, new ArrayList<>());
            }
            try (var scanner = new Scanner(new File(path))) {
                while (scanner.hasNext()) {
                    String[] scan = scanner.nextLine().split(delimiter);
                    for (int i = 0; i < scan.length; i++) {
                        map.get(columns[i]).add(scan[i]);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        StringJoiner res = new StringJoiner("\n", "", "\n");
        int index = 0;
        while (index < map.size()) {
            StringJoiner joiner = new StringJoiner(delimiter);
            for (String f : filters) {
                String s = map.get(f).get(index);
                joiner.add(s);
            }
            index++;
            res.merge(joiner);
        }
        try (FileWriter writer = new FileWriter(out)) {
            writer.write(res.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        checkArgs("path", argsName.get("path"));
        checkArgs("delimiter", argsName.get("delimiter"));
        checkArgs("out", argsName.get("out"));
        checkArgs("filter", argsName.get("filter"));
        handle(argsName);
    }

    private static void checkArgs(String key, String arg) {
        switch (key) {
            case "path":
                if (!".csv".contains(arg)) {
                    throw new IllegalArgumentException("Error: file for read must has '.csv' extension");
                }
                break;
            case "delimiter":
                if (!";".equals(arg)) {
                    throw new IllegalArgumentException("Error: delimiter must be ';'");
                }
                break;
            case "out":
                if (!"stdout".equals(arg)) {
                    throw new IllegalArgumentException("Error: output information must be stdout");
                }
                break;
            case "filter":
                if (arg.isEmpty()) {
                    throw new IllegalArgumentException("Error: empty filter");
                }
                break;
            default:
                break;
        }
    }
}