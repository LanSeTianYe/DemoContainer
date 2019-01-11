package com.xiaotian.demo.test.poi;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

/**
 * 构造Xml文件方法流程:init() -> addTitle() ->  addHeader() -> addContent() -> writeTo() -> finish()
 * <p>其中addTitle、addHeader、addContent顺序可以调整，且可以不存在。
 * </p>
 *
 */
public class XmlFileBuilder {

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFCreationHelper helper;
    private int rowNum = 0;

    public XmlFileBuilder init() {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet();
        helper = wb.getCreationHelper();
        return this;
    }

    /**
     * 添加标题
     *
     * @param cellData 标题内容
     * @param width    标题占单元格的列侯数
     * @param function 函数，通过 HSSFWorkbook 创建样式
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 15:12
     */
    public XmlFileBuilder addTitle(CellData cellData, int width, Function<HSSFWorkbook, HSSFCellStyle> function) {
        HSSFCellStyle style = function.apply(wb);
        HSSFRow titleRow = sheet.createRow(rowNum);
        CellRangeAddress c1 = new CellRangeAddress(0, 0, 0, width);
        sheet.addMergedRegion(c1);
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue(cellData.getValue());
        addRowNum(1);
        return this;
    }

    /**
     * 添加表头 |时间|新增粉丝数|取消关注人数|净增粉丝数|
     *
     * @param header   表头数据
     * @param function 函数，通过 HSSFWorkbook 创建样式
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 15:19
     */
    public XmlFileBuilder addHeader(List<CellData> header, Function<HSSFWorkbook, HSSFCellStyle> function) {
        HSSFCellStyle style = function.apply(wb);
        createRow(header, style);
        return this;
    }

    /**
     * 填充表格数据，二维数组[行,列]
     *
     * @param data     要填充的数据
     * @param function 函数，通过 HSSFWorkbook 创建样式
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 15:19
     */
    public XmlFileBuilder addContent(List<List<CellData>> data, Function<HSSFWorkbook, HSSFCellStyle> function) {
        HSSFCellStyle style = function.apply(wb);
        for (int i = 0; i < data.size(); i++) {
            List<CellData> rowData = data.get(i);
            createRow(rowData, style);
        }
        return this;
    }

    /**
     * 把表格数据写入到输出流中，写入之后会关闭创建的表格，但不会关闭输出流
     *
     * @param outputStream 表格数据写入的地方
     * @throws IOException 些数据出错或者关闭构造的表格失败时抛出 IOException
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 15:18
     */
    public XmlFileBuilder writeTo(OutputStream outputStream) throws IOException {
        wb.write(outputStream);
        return this;
    }

    /**
     * 完成文件构造,关闭在内存中创建的表格
     *
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 15:23
     */
    public void finish() throws IOException {
        wb.close();
    }

    private void createRow(List<CellData> data, HSSFCellStyle style) {
        HSSFRow headerRow = sheet.createRow(rowNum);
        int baseWidth = 256 * 2;
        for (int j = 0; j < data.size(); j++) {
            int width = data.get(j).getValue().getBytes().length * baseWidth;
            sheet.setColumnWidth(j, width);
            HSSFCell newCell = headerRow.createCell(j);
            CellData cellData = data.get(j);

            boolean isDateType = cellData instanceof DateCellData;
            if (isDateType) {
                HSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.cloneStyleFrom(style);
                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(((DateCellData) cellData).getDateFormat()));
                newCell.setCellStyle(cellStyle);
                newCell.setCellValue(cellData.getValue());
                continue;
            }

            boolean isLinkType = cellData instanceof LinkCellData;
            if (isLinkType) {
                HSSFCellStyle linkStyle = XmlStyle.linkStyle(wb);
                HSSFHyperlink hyperlink = helper.createHyperlink(HyperlinkType.URL);
                hyperlink.setAddress(((LinkCellData) cellData).getAddress());
                newCell.setHyperlink(hyperlink);
                newCell.setCellStyle(linkStyle);
                newCell.setCellValue(cellData.getValue());
                continue;
            }
            newCell.setCellStyle(style);
            newCell.setCellValue(cellData.getValue());
        }
        addRowNum(1);
    }

    private void addRowNum(int count) {
        rowNum++;
    }
}
