package top.mall.console.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Utils {
    private static String password_safe_code = "2KPsTgQH#N93mqbIkqG9B@ga&7oR@M";
    private static String charsetName = "UTF-8";
    public static String digestPassword(String text) {
        if(text == null) {
            return null;
        }
        text += password_safe_code;
        try {
            return digest(text.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String digest(byte[] bytes) {
        try {
            byte[] digestedBytes = MessageDigest.getInstance("MD5").digest(bytes);
            return bytesToString(digestedBytes);
        } catch (Exception var2) {
            return "";
        }
    }

    private static String bytesToString(byte[] digest) {
        StringBuilder hexString = new StringBuilder();

        for(int i = 0; i < digest.length; ++i) {
            hexString.append(Integer.toHexString(digest[i] & 255 | -256).substring(6));
        }

        return hexString.toString();
    }
}
