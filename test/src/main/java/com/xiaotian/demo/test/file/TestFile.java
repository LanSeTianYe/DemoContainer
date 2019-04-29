package com.xiaotian.demo.test.file;

import java.io.File;

public class TestFile {

    public static void main(String[] args) {
        File file = new File("D:");

        for (File listFile : file.listFiles()) {
            System.out.println(listFile.getParent());
            if (listFile.isDirectory()) {
                for (File file1 : listFile.listFiles()) {
                    System.out.println(file1.getParent());
                    if (file1.isDirectory()) {
                        for (File file2 : file1.listFiles()) {
                            System.out.println(file2.getAbsolutePath());
                            System.out.println(file2.getAbsoluteFile().getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
