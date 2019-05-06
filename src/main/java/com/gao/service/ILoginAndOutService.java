package com.gao.service;

import com.gao.dto.UserDto;
import com.gao.entity.User;
import com.gao.result.Result;

/**
 * @Author gs
 * @Date created time 2019/5/5 11:59
 * @Description
 */
public interface ILoginAndOutService {

    Result loginVarify(User user);

    Result logout(String token);
}
