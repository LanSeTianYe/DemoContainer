package com.xiaotian.demo.test.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class IPAndNumberTranslateUtilTest {

    @Test
    public void ipToNumber() {
        String finalIp = "255.255.255.255";
        Long maxIpNumber = IPAndNumberTranslateUtil.IpToNumber(finalIp);
        for (Long i = 0L; i < maxIpNumber; i = i + 10000L) {
            System.out.println(IPAndNumberTranslateUtil.numberToIp(i));
            assertEquals(i, IPAndNumberTranslateUtil.IpToNumber(IPAndNumberTranslateUtil.numberToIp(i)));
        }
    }
}