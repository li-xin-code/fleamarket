package com.lixin.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author com.lixin
 */
public class SystemUtils {

    public static final String USERNAME_REGEX = "^[\\w_\u4e00-\u9fa5\\-]{2,12}$";
    public static final String PASSWORD_REGEX = "^[\\w_!@#$%^&*~+-=]{6,16}$";
    private static final String NUMBER_REGEX = "^[0-9]+$";

    private final static String[] CHARS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private SystemUtils() {
    }

    public static boolean isNumber(String s) {
        return Pattern.matches(NUMBER_REGEX, s);
    }

    /**
     * 生成一个8位的62进制数
     * Generate an 8-bit Binary 62 number.
     *
     * @return uuid
     */
    public static String uuid() {
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int len = 8;
        for (int i = 0; i < len; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            builder.append(CHARS[x % 0x3E]);
        }
        return builder.toString();
    }

    public static String encryptToMd5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String dateFormat(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy年MM⽉dd⽇ HH:mm:ss").format(dateTime);
    }
}
