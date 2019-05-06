package com.gao.utils;

import java.util.UUID;

/**
 * @Author gs
 * @Date created time 2019/5/5 12:19
 * @Description 生成token
 */
public class TokenProccessor {

    public static String makeToken(){
        String token = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        return token;
    }
}
