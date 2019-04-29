package com.gao.result;

/**
 * @Author gs
 * @Date created time 2019/4/29 21:42
 * @Description
 */
public class ResultFactory {

    public static Result getSuccessResult(Object data){
        Result result = new Result<>(data);
        return result;
    }

    public static Result getErrorResult(ResultEnum enm){
        Result result = new Result<>(enm.getCode(),enm.getMessage());
        return result;
    }

}
