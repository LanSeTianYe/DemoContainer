package com.xiaotian.demo.test.poi;

public class DateCellData extends CellData {

    private String dateFormat = "yyyy-mm-dd";

    public DateCellData(String value) {
        super(value);
    }

    public DateCellData(String value, String dateFormat) {
        super(value);
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }
}
