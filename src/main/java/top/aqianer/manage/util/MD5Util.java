package top.aqianer.manage.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5Hex(String input) {
        return DigestUtils.md5Hex(input);
    }
}