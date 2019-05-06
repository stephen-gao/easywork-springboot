package com.gao.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.dao.UserMapper;
import com.gao.entity.RegisterUser;
import com.gao.entity.User;
import com.gao.result.Result;
import com.gao.result.ResultEnum;
import com.gao.result.ResultFactory;
import com.gao.service.IUserService;
import com.gao.utils.MD5;
import com.gao.utils.SaltUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 *   服务实现类
 * </p>
 *
 * @author gaosheng
 * @since 2019-04-29
 */
@EnableCaching
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getUserPage(Page<User> page, User user) {
        QueryChainWrapper<User> wrapper = new QueryChainWrapper<>(userMapper);
        if(!StringUtils.isEmpty(user.getUsername())) {
            wrapper.like("username", user.getUsername());
        }
        if(!StringUtils.isEmpty(user.getTel())) {
            wrapper.eq("tel", user.getTel());
        }
        if(null != user.getStatus()){
            wrapper.eq("status",user.getStatus());
        }
        return wrapper.page(page);
    }

    /**
     * @Author gs
     * @Date created time 2019/4/30 11:47
     * @param user 用户
     * @return java.lang.Boolean
     * @Description 验证用户名是否已存在 true/存在   false/不存在
     */
    @Override
    public Boolean varifyUsernameExist(User user) {

        QueryWrapper wrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(user.getId())) {
            wrapper.ne("id", user.getId());
        }
        wrapper.eq("username",user.getUsername());
        User one = userMapper.selectOne(wrapper);
        if(null != one && null != one.getId()){
            return true;
        }
        return false;
    }

    @Override
    public void addUser(RegisterUser user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setEditTime(date);
        String salt = SaltUtils.getSalt();
        user.setSalt(salt);
        String encrypt = MD5.encrypt(user.getPassword(),user.getUsername(),user.getSalt());
        user.setPassword(encrypt);
        logger.info("新建用户信息: [{}]", JSON.toJSONString(user));
        userMapper.addUser(user);
        logger.info("新建用户信息成功: [{}]",JSON.toJSONString(user));
    }

    @Override
    public void updateUserByUserId(User user) {
        user.setPassword(null);
        user.setEditTime(new Date());
        logger.info("更新用户=>[{}] 信息: [{}]",user.getId(),JSON.toJSONString(user));
        updateById(user);
        logger.info("更新用户=>[{}] 信息成功: [{}]",user.getId(),JSON.toJSONString(user));
    }




}
