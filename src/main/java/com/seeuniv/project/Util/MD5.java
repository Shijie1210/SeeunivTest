package com.seeuniv.project.Util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    /**
     * 将字符串转化为MD5码
     *
     * @param tempString
     * @return
     */
    public static String change2MD5(String tempString) {
        byte[] oldBytes = tempString.getBytes();
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] newBytes = mDigest.digest(oldBytes);
        BASE64Encoder encoder = new BASE64Encoder();
        String newStr = encoder.encode(newBytes);
        return newStr;
    }
}
