package com.xiaotian.demo.test.nio;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Spliterator;

public class TestFileSystem {

    public static void main(String[] args) {
        FileSystem defaultSystem = FileSystems.getDefault();

        Spliterator<FileStore> storeSpliterator = defaultSystem.getFileStores().spliterator();
        storeSpliterator.forEachRemaining(fileStore -> {
            try {
                System.out.println(fileStore.getTotalSpace());
                System.out.println(fileStore.getUsableSpace());
                System.out.println(fileStore.getUnallocatedSpace());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(defaultSystem);
    }
}
