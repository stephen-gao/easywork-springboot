package com.gao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.entity.RegisterUser;
import com.gao.entity.User;
import com.gao.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gaosheng
 * @since 2019-04-29
 */
public interface IUserService extends IService<User> {

    IPage<User> getUserPage(Page<User> page, User user);
    /**
     * @Author gs
     * @Date created time 2019/4/30 11:45
     * @param user 用户
     * @return Boolean
     * @Description 验证用户名是否已存在
     */
    Boolean varifyUsernameExist(User user);

    void addUser(RegisterUser user);

    void updateUserByUserId(User user);

}
