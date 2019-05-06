package com.gao.utils;

import java.security.SecureRandom;

/**
 * @Author gs
 * @Date created time 2019/4/30 16:10
 * @Description
 */
public class SaltUtils {

    public static String getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] aesKey = new byte[16];
        random.nextBytes(aesKey);
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt();
        System.out.println(salt);
    }
}
