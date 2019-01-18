package com.xiaotian.demo.test.poi;


public class LinkCellData extends  CellData{

    private String address;

    public LinkCellData(String value, String link) {
        super(value);
        this.address = link;
    }

    public String getAddress(){
        return address;
    }
}
