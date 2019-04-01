package com.xiaotian.demo.algorithm.leetcode;

import java.io.File;
import java.nio.file.Paths;

public class ChangeFileName {

    public static void main(String[] args) {
        File parent = Paths.get("D:\\work\\workspace\\github\\DemoContainer\\algorithm\\src\\main\\java\\com\\xiaotian\\demo\\algorithm\\leetcode\\").toFile();
        File[] files = parent.listFiles();

        for (File file : files) {
            String fileName = file.getName();
            if (fileName.startsWith("A")) {
                continue;
            } else {
                String[] split = fileName.split("\\.");
                if (split[0].equalsIgnoreCase("ChangeFileName")) {
                    continue;
                }
                String[] nameAndNumber = split[0].split("_");
                String newFileName = "A_" + nameAndNumber[1] + "_" + nameAndNumber[0];
                System.out.println(newFileName);

//                file.renameTo(new File(parent.getAbsolutePath() + File.separator + newFileName + "." + split[1]));
            }
        }
    }
}
