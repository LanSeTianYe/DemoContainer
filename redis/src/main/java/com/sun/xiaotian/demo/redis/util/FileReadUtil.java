package com.sun.xiaotian.demo.redis.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadUtil {

    private final static String luaDirectory = "redis" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "lua";

    /**
     * 读取资源目录下的文件
     *
     * @param fileName
     * @return
     */
    public static byte[] readFromResource(String fileName) throws IOException {
        try (ByteArrayOutputStream temp = new ByteArrayOutputStream()) {
            Path path = Paths.get(luaDirectory + File.separator + fileName);
            Files.copy(path, temp);
            return temp.toByteArray();
        }
    }
}
