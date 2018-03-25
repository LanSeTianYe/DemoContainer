package com.sun.xiaotian.demo.elasticsearch.initdata.helper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 读取文件的创建日期和修改日期
 */

public abstract class FileDateHelper {

    /**
     * 获取文件的创建日期
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    public static long getCreateDate(File file) throws IOException {
        Path path = file.toPath();
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        return basicFileAttributes.creationTime().toMillis();
    }

    public static long getFileSize(File file) throws IOException {
        Path path = file.toPath();
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        return basicFileAttributes.size();
    }
}
