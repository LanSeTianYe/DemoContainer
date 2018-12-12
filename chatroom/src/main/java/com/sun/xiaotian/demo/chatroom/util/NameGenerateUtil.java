package com.sun.xiaotian.demo.chatroom.util;

import com.sun.xiaotian.demo.chatroom.exception.ChatRoomException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 姓名生成器
 */
public class NameGenerateUtil {

    private static final String path = new File("").getAbsolutePath() +
            File.separator + "src" +
            File.separator + "main" +
            File.separator + "resources";

    private static final Random random = new SecureRandom();

    private static final String surnames[];

    private static final StringBuilder name = new StringBuilder();

    public static String generate() {
        int surnamesIndex = random.nextInt(surnames.length);
        int nameWordNumber = random.nextInt(2);
        nameWordNumber++;
        String tempName = "";
        for (int i = 0; i < nameWordNumber; i++) {
            int nameIndex = random.nextInt(name.length());
            tempName = String.format("%s%s", tempName, name.charAt(nameIndex));
        }
        return surnames[surnamesIndex] + tempName;
    }

    static {
        String surnamesPath = path + File.separator + "surnames.txt";
        String namePath = path + File.separator + "name.txt";

        try {
            Set<Character> tempSet = new HashSet<>();
            byte[] surnamesBytes = Files.readAllBytes(Paths.get(surnamesPath));
            byte[] nameBytes = Files.readAllBytes(Paths.get(namePath));
            String surnamesText = new String(surnamesBytes, Charset.forName("UTF-8"));
            String nameText = new String(nameBytes, Charset.forName("UTF-8"));
            surnames = surnamesText.split("、");
            for (int i = 0; i < nameText.length(); i++) {
                char c = nameText.charAt(i);
                if (Character.getType(c) == Character.getType('孙')) {
                    if (tempSet.add(c)) {
                        name.append(c);
                    }
                }
            }
        } catch (IOException e) {
            throw new ChatRoomException(e.getMessage(), e);
        }
    }
}


