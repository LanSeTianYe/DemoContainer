package com.sun.xiaotian.demo.elasticsearch.initdata.helper;


import com.github.stuxuhai.jpinyin.PinyinException;
import com.sun.xiaotian.demo.elasticsearch.initdata.model.FileInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LoadCatalogueTreeHelper {

    private final static Logger logger = LogManager.getLogger(LoadCatalogueTreeHelper.class);

    private String filePath;

    /**
     * 加载目录下面的文件结构
     *
     * @param file
     * @return
     */
    public List<FileInfo> loadByDirectory(java.io.File file) throws IOException, NoSuchAlgorithmException, PinyinException {
        filePath = file.getAbsolutePath();

        List<FileInfo> fileInfoList = new ArrayList<>();

        if (!file.exists() || !file.isDirectory()) {
            throw new RuntimeException(file + "必须是文件夹!");
        }

        loadByDirectory(file, FileInfo.ROT_ID, fileInfoList);
        return fileInfoList;
    }

    /**
     * 递归加载目录下面的文件，放入list中
     *
     * @param file         文件
     * @param parentId     所属目录id
     * @param fileInfoList 存放文件列表
     * @throws IOException
     */
    private void loadByDirectory(java.io.File file, String parentId, List<FileInfo> fileInfoList) throws IOException, NoSuchAlgorithmException, PinyinException {

        if (file.getName().startsWith(".")) {
            return;
        }

        FileInfo tempFileInfo = new FileInfo();
        tempFileInfo.setId(ChineseToPinYinHelper.from(FileInfo.prefix, file.getName(), Md5CodeHelper.getInstance().getMd5Code(file.getName())));
        tempFileInfo.setParentId(parentId);
        tempFileInfo.setPath(file.getAbsolutePath().replace(filePath, ""));
        tempFileInfo.setStatue("Active");

        if (file.isFile()) {
            if (!file.getName().endsWith(".md")) {
                return;
            } else {
                tempFileInfo.setHasChild(false);
                tempFileInfo.setFileName(file.getName().replaceAll(".md", ""));
            }
        } else if (file.isDirectory()) {
            tempFileInfo.setFileName(file.getName());

            tempFileInfo.setHasChild(true);
            java.io.File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                loadByDirectory(files[i], tempFileInfo.getId(), fileInfoList);
            }
        }

        tempFileInfo.setFileSize(FileDateHelper.getFileSize(file));
        tempFileInfo.setCreateDate(new Date(FileDateHelper.getCreateDate(file)));
        tempFileInfo.setUpdateDate(new Date(file.lastModified()));
        fileInfoList.add(tempFileInfo);
    }
}


