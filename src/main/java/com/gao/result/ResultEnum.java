package com.gao.result;

/**
 * @Author gs
 * @Date created time 2019/4/29 17:57
 * @Description
 */
public enum ResultEnum {
    SUCCESS(10000,"操作成功"),//成功
    FAIL(40000,"操作失败"),//失败
    UNAUTHORIZED(40001,"权限不足"),//未认证（签名错误）
    PARAM_ERROR(40002,"传参有误"),//失败
    NOT_FOUND(40004,"接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(50000,"服务器错误");//服务器内部错误;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
