package com.xiaotian.demo.test.cl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileSystemClassLoader extends ClassLoader {

    private final String classPath;

    public FileSystemClassLoader(String filePath) {
        this.classPath = filePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] fileData = getFileData(name);
        if (fileData == null) {
            return super.loadClass(name);
        }
        return defineClass(name, fileData, 0, fileData.length);
    }

    private byte[] getFileData(String name) {
        String fileName = classToFileName(name);
        File file = new File(classPath + fileName);
        if (!file.exists()) {
            return null;
        }

        try (InputStream ins = new FileInputStream(file);
             ByteArrayOutputStream tempCache = new ByteArrayOutputStream()) {
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                tempCache.write(buffer, 0, bytesNumRead);
            }
            return tempCache.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classToFileName(String className) {
        int index = className.lastIndexOf(".");
        return className.substring(index + 1) + ".class";
    }
}
