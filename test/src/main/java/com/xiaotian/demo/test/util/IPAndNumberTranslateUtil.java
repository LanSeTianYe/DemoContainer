package com.xiaotian.demo.test.util;

public class IPAndNumberTranslateUtil {

    public static Long IpToNumber(String ipAddress) {
        String[] ipNUmber = ipAddress.split("\\.");
        return (Long.valueOf(ipNUmber[0]) << 24) + (Long.valueOf(ipNUmber[1]) << 16) + (Long.valueOf(ipNUmber[2]) << 8) + (Long.valueOf(ipNUmber[3]));
    }

    public static String numberToIp(Long ipNumber) {
        String result = "";
        result += (ipNumber >> 24) + ".";
        result += ((ipNumber << (8 + 32)) >>> 56) + ".";
        result += ((ipNumber << (16 + 32)) >>> 56) + ".";
        result += ((ipNumber << (24 + 32)) >>> 56);
        return result;
    }
}
