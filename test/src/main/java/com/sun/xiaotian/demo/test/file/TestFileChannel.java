package com.sun.xiaotian.demo.test.file;


import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class TestFileChannel {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0");

    public static void main(String[] args) throws IOException {
        File toFile = new File("e:" + File.separator + "blue.mp4");
        FileChannel open = FileChannel.open(toFile.toPath(), StandardOpenOption.READ);

        File fromFile = new File("f:" + File.separator + toFile.getName());
        if (fromFile.exists()) {
            fromFile.delete();
        }

        if (fromFile.createNewFile()) {
            FileChannel from = FileChannel.open(fromFile.toPath(), StandardOpenOption.WRITE);
            listenerFileSize(Files.size(toFile.toPath()), fromFile.toPath());
            open.transferTo(0, Files.size(toFile.toPath()), from);
        } else {
            System.err.println("error");
        }
    }

    private static void listenerFileSize(long finallyFileSize, Path path) {
        new Thread(() -> {
            try {
                long time = System.currentTimeMillis();
                while (true){
                    long currFileSize = Files.size(path);
                    System.out.println("当前进度: " + decimalFormat.format(currFileSize * 1.0 * 100 / finallyFileSize) + " %");
                    TimeUnit.MILLISECONDS.sleep(500);
                    if(currFileSize == finallyFileSize) {
                        break;
                    }
                }
                System.out.println("耗费时间: " + (System.currentTimeMillis() - time) + " 毫秒");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
