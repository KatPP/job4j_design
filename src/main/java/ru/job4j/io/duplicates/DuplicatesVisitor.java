package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<String>> mapOfFiles;

    public DuplicatesVisitor() {
        this.mapOfFiles = new HashMap<>();
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileKey = new FileProperty(
                file.toFile().length() / 1024,
                file.getFileName().toString());
        mapOfFiles.computeIfAbsent(fileKey, k -> new ArrayList<>())
                .add(file.toAbsolutePath().toString());
        return FileVisitResult.CONTINUE;
    }
    public Map<FileProperty, List<String>> getMapOfFiles() {
        return mapOfFiles;
    }
}