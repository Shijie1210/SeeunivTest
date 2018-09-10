package com.seeuniv.project.Util;

import java.util.Random;

public class RandomCheckCode {
    private static final String checkWord = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 获取随机的用户激活验证码
     *
     * @return
     */
    public static String checkCode() {
        System.out.println(checkWord.charAt(3));
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += checkWord.charAt(random.nextInt(62)) + "";
        }
        return code;
    }
}
