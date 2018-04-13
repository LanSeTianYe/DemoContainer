package com.sun.xiaotian.demo.test.poi.extractor;

import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.IOException;

public class ExtractorFactoryTest {

    public static void main(String[] args) throws OpenXML4JException, XmlException, IOException {

        String filePath = "D:" + File.separator + "test.xls";
        File file = new File(filePath);
        POITextExtractor extractor = ExtractorFactory.createExtractor(file);
        //获取文件文本内容
        String content = extractor.getText();
        System.out.println(content);

        POITextExtractor metadataTextExtractor = extractor.getMetadataTextExtractor();
        String metadataTextExtractorText = metadataTextExtractor.getText();
        System.out.println(metadataTextExtractorText);
    }
}
