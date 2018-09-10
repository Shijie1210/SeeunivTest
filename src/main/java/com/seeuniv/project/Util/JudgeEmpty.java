package com.seeuniv.project.Util;

import com.seeuniv.project.domain.MiddleUser;

public class JudgeEmpty {

    public static boolean isObjectNull(Object src) {
        if (src == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        MiddleUser mid = new MiddleUser();
        mid.setEmail("shikjlk");
        boolean flag = isObjectNull(mid);
        System.out.println(flag);
    }
}
