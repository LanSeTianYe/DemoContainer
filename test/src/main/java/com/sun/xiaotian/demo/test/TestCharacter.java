package com.sun.xiaotian.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCharacter {

    private static final Pattern notContainsChar = Pattern.compile("[&‘’“”/\\\\\t\n\r]");

    private static final Pattern halfLengthChar = Pattern.compile("[!-}]");

    private static final Pattern allchar = Pattern.compile("[\\w]");

    public static void main(String[] args) {
        String quan = "，";
        String hanzi = "，";
        System.out.println(quan.equals(hanzi));

        System.out.println(notContainsChar.matcher("\n").find());
        System.out.println(notContainsChar.matcher("\r").find());
        System.out.println(notContainsChar.matcher("\t").find());
        System.out.println(notContainsChar.matcher("&").find());
        System.out.println(notContainsChar.matcher("‘").find());
        System.out.println(notContainsChar.matcher("“").find());
        System.out.println(notContainsChar.matcher("汉字asdasd9009090312_").find());


        int halfCharNumber = 0;
        Matcher matcher = halfLengthChar.matcher("aZ09,.\"");
        while (matcher.find()) {
            halfCharNumber++;
        }
        System.out.println(halfCharNumber);

        for (int i = 0; i < 256; i++) {
            System.out.println(i + " : " + ((char) i));
        }

        System.out.println(((int) 'ａ'));
        System.out.println(" das d da sdsa ".trim());
    }

    /**
     *
     * @return
     */
    public boolean containsVallidCharacter(String text) {
        return notContainsChar.matcher(text).find();
    }
}
