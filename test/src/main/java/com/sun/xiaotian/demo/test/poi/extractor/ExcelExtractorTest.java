package com.sun.xiaotian.demo.test.poi.extractor;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelExtractorTest {

    public static void main(String[] args) throws IOException {

        String filePath = "D:" + File.separator + "test.xls";
        File file = new File(filePath);
        ExcelExtractor excelExtractor = new ExcelExtractor(new HSSFWorkbook(new FileInputStream(file)));

        String content = excelExtractor.getText();
        System.out.println(content);

        //获取文档 作者等信息
        SummaryInformation summaryInformation = excelExtractor.getSummaryInformation();
    }
}