package com.sun.xiaotian.demo.test.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public interface XmlStyle {

    static HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 13);
        titleFont.setFontName("宋体");
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    static HSSFCellStyle linkStyle(HSSFWorkbook workbook) {
        HSSFCellStyle linkStyle = workbook.createCellStyle();
        HSSFFont linkFont = workbook.createFont();
        linkFont.setUnderline(HSSFFont.U_SINGLE);
        linkFont.setColor(HSSFColor.BLUE.index);
        linkStyle.setFont(linkFont);
        return linkStyle;
    }

    static HSSFCellStyle defaultStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFont(font);
        return style;
    }
}
