package com.xiaotian.demo.test.poi;

public abstract class CellData {

    private String value;

    public CellData(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
