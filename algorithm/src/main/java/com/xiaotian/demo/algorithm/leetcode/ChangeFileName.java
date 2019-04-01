package com.xiaotian.demo.algorithm.leetcode;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class ChangeFileName {

    public static void main(String[] args) {
        File parent = Paths.get("D:\\work\\workspace\\github\\DemoContainer\\algorithm\\src\\main\\java\\com\\xiaotian\\demo\\algorithm\\leetcode\\").toFile();
        File[] files = parent.listFiles();

        for (File file : files) {
            String oldFileName = file.getName();
            if (oldFileName.startsWith("A_")) {
                continue;
            } else {
                String[] nameAndSuffix = oldFileName.split("\\.");
                if (nameAndSuffix[0].equalsIgnoreCase("ChangeFileName")) {
                    continue;
                }
                String[] nameAndNumber = nameAndSuffix[0].split("_");
                String newFileName = "A_" + nameAndNumber[1] + "_" + nameAndNumber[0];
                System.out.println(newFileName);
                replaceFileName(parent, file, nameAndSuffix[0], newFileName);
            }
        }
    }

    private static void replaceFileName(File parent,File file, String oldFileName, String newFileName) {
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            for (int i = 0; i < strings.size(); i++) {
                if (strings.get(i).contains(oldFileName)) {
                    strings.set(i, strings.get(i).replaceAll(oldFileName, newFileName));
                }
            }
            String newFile = parent.getAbsolutePath() + File.separator + newFileName + ".java";
            Files.write(Paths.get(newFile), strings, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
