package com.xiaotian.demo.kafka.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午13:44
 */
public class DateFormatUtil {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");

    public static String getDateStr(Date date) {
        return simpleDateFormat.format(date);
    }
    
    public static void main(String[] args) {
        System.out.println(getDateStr(new Date()));
    }
}
