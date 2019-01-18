package com.xiaotian.demo.test.poi;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年03月19日 下午20:49
 */
public class XmlFileBuilderTest {

    @Test
    public void testBuild() throws Exception {
        XmlFileBuilder xmlFileBuilder = new XmlFileBuilder();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:" + File.separator + "test.xls"));
        xmlFileBuilder
                .init()
                .addTitle(new TextCellData("我是标题"), 10, XmlStyle::titleStyle)
                .addHeader(getHeader(10), XmlStyle::titleStyle)
                .addContent(getContent(10, 10), XmlStyle::defaultStyle)
                .writeTo(fileOutputStream)
                .finish();
    }

    private List<CellData> getHeader(int length) {
        List<CellData> stringList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                stringList.add(new DateCellData("2015-08-08", "yyyy-mm-dd"));
            } else if (i == 1) {
                stringList.add(new LinkCellData("百度", "http://www.baidu.com"));
            } else {
                stringList.add(new TextCellData(i + "    "));
            }
        }
        return stringList;
    }

    private List<List<CellData>> getContent(int height, int rows) {
        List<List<CellData>> results = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            results.add(getHeader(rows));
        }

        return results;
    }
}