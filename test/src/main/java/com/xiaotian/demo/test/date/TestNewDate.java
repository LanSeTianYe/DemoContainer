package com.xiaotian.demo.test.date;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNewDate {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public static void main(String[] args) {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2 = new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24));
        Date date3 = new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 100));
        showDate(date1);
        showDate(date2);
        showDate(date3);
    }

    private static void showDate(Date date) {
        System.out.println(date.getTime());
        System.out.println(simpleDateFormat.format(date));
    }
}
