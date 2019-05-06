package com.gao.result;

/**
 * @Author gs
 * @Date created time 2019/4/29 21:42
 * @Description
 */
public class ResultFactory {

    public static Result getDataResult(Object data){
        Result result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static Result getDefaultResult(ResultEnum enm){
        Result result = new Result<>(enm.getCode(),enm.getMessage());
        return result;
    }

    public static Result getMessgaeResult(ResultEnum enm,String message){
        Result result = new Result<>(enm.getCode(),message);
        return result;
    }

}
