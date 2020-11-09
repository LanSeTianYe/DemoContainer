package com.xiaotian.demo.test.bit;

public class PrintTable {

    private static final char[] BASE_64_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private static final char[] BASE_32_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '2', '3', '4', '5', '6', '7',
    };

    public static void main(String[] args) {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            data.append("|");
            data.append(applenZeroToEight(Integer.toBinaryString(i)));
            data.append("|");
            data.append(Integer.toOctalString(i));
            data.append("|");
            data.append(i);
            data.append("|");
            data.append(Integer.toHexString(i));
            data.append("|");
            if (i < BASE_32_TABLE.length) {
                data.append(BASE_32_TABLE[i]);
            } else {
                data.append(" ");
            }
            data.append("|");
            if (i < BASE_64_TABLE.length) {
                data.append(BASE_64_TABLE[i]);
            } else {
                data.append(" ");
            }
            data.append("|");
            if (i == 32) {
                data.append("空格");
            }
            if (i >= 126 || i == 13 || i == 10 | i == 9) {
                data.append(" ");
            } else {
                data.append(((char) i));
            }
            data.append("|");
            if (i == 32) {
                data.append("空格");
            } else if (i == 13 || i == 10 | i == 9) {
                data.append(" ");
            } else {
                data.append(((char) i));
            }
            data.append("|");
            data.append("\n");
        }
        System.out.println(data.toString());
    }

    private static String applenZeroToEight(String value) {
        StringBuilder temp = new StringBuilder(value).reverse();
        if (value.length() <= 8) {
            while (true) {
                int length = temp.length();
                if (length < 8) {
                    temp.append("0");
                } else {
                    break;
                }
            }
        }
        return temp.reverse().insert(4, " ").toString();
    }
}
