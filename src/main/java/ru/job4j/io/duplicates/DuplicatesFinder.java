package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<String>> map = visitor.getMapOfFiles();
        for (Map.Entry entry : map.entrySet()) {
            List<String> paths = (List<String>) entry.getValue();
            FileProperty key = (FileProperty) entry.getKey();
            if (paths.size() > 1) {
                StringBuilder builder = new StringBuilder();
                builder.append(key.getName()).append("-").append(key.getSize()).append("Kb: ");
                System.out.println(builder);
                paths.forEach(System.out::println);
                System.out.println();
            }
        }
    }
}