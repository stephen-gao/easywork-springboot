package com.gao.entity;

/**
 * @Author gs
 * @Date created time 2019/4/30 16:02
 * @Description
 */
public class RegisterUser extends User{

    private static final long serialVersionUID = 1L;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
