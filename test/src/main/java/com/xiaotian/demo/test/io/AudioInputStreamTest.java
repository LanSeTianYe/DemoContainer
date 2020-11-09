package com.xiaotian.demo.test.io;

import java.io.IOException;
import java.util.Scanner;

public class AudioInputStreamTest {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            System.out.println(scanner.nextLong());
        }

    }
}
