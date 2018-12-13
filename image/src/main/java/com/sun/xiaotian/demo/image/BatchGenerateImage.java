package com.sun.xiaotian.demo.image;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量编辑图片
 */
public class BatchGenerateImage {

    private static final List<ImageSize> imagesSize = new ArrayList<>();

    //源文件目录
    private static final File fileSourceDirectory = new File("C:\\Users\\sunfeilong\\Desktop\\source");
    //生成图片的目录
    private static final File fileDestinationDirectory = new File("C:\\Users\\sunfeilong\\Desktop\\source\\destination");

    //初始化
    static {
        imagesSize.add(new ImageSize(1000, 560));
        imagesSize.add(new ImageSize(240, 180));
        imagesSize.add(new ImageSize(800, 450));
        imagesSize.add(new ImageSize(960, 540));
        imagesSize.add(new ImageSize(960, 334));
        imagesSize.add(new ImageSize(354, 222));
        imagesSize.add(new ImageSize(1080, 606));
        if (!fileDestinationDirectory.exists()) {
            if (!fileDestinationDirectory.mkdir()) {
                throw new IllegalStateException("创建文件夹失败");
            }
        }
    }

    public static void main(String[] args) {
        File[] files = fileSourceDirectory.listFiles();
        imagesSize.forEach(imageSize -> {
            try {
                for (File file : files) {
                    if (!file.isFile()) {
                        continue;
                    }
                    File temp = new File(fileDestinationDirectory.getAbsolutePath() + "\\" + file.getName().split("\\.")[0] + "_" + imageSize.width + "_" + imageSize.height + ".jpg");
                    if (!temp.exists()) {
                        if (!temp.createNewFile()) {
                            throw new IllegalStateException("创建文件失败");
                        }
                    }

                    FileOutputStream fileOutputStream = new FileOutputStream(temp);
                    Thumbnails
                            .of(file)
                            .outputFormat("jpg")
                            .forceSize(imageSize.width, imageSize.height)
                            .toOutputStream(fileOutputStream);
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    static class ImageSize {
        private final int width;
        private final int height;

        ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
