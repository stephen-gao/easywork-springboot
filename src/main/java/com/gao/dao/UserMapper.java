package com.gao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.entity.RegisterUser;
import com.gao.entity.User;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author gaosheng
 * @since 2019-04-29
 */
public interface UserMapper extends BaseMapper<User> {
    void addUser(RegisterUser user);

    RegisterUser selectRegisterUser(User user);
}