package com.sun.xiaotian.demo.elasticsearch.initdata.helper;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成 md5 编码
 */

public class Md5CodeHelper {

    private Md5CodeHelper() {

    }

    public static Md5CodeHelper getInstance() {
        return InitMd5Helper.md5CodeHelper;
    }

    /**
     * 获取给定字符串的MD5编码
     *
     * @param str 字符串
     * @return
     */
    public String getMd5Code(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    static class InitMd5Helper {
        private final static Md5CodeHelper md5CodeHelper = new Md5CodeHelper();
    }
}
