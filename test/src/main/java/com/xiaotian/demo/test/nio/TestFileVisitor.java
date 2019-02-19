package com.xiaotian.demo.test.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class TestFileVisitor {

    public static void main(String[] args) throws IOException {

        Files.walkFileTree(Paths.get("D:\\work"), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println(String.format(" pre ----[%s]", dir.getFileName()));
                if (!dir.toFile().isHidden()) {
                    return FileVisitResult.CONTINUE;
                } else {
                    return FileVisitResult.SKIP_SUBTREE;
                }
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println(String.format("visit ----[%s]", file.getFileName()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                System.out.println(String.format(" pre ----[%s]", dir.getFileName()));
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
