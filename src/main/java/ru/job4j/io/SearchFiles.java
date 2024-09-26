package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> result;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        this.result = new ArrayList<>();
    }

    public List<Path> getPaths() {
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
        if (condition.test(file)) {
            result.add(file.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }
}
