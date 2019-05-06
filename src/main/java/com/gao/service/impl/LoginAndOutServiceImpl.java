package com.gao.service.impl;

import com.gao.cache.ICache;
import com.gao.dao.UserMapper;
import com.gao.dto.UserDto;
import com.gao.entity.RegisterUser;
import com.gao.entity.User;
import com.gao.result.Result;
import com.gao.result.ResultEnum;
import com.gao.result.ResultFactory;
import com.gao.service.ILoginAndOutService;
import com.gao.utils.MD5;
import com.gao.utils.TokenProccessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author gs
 * @Date created time 2019/5/5 12:00
 * @Description
 */
@Service
public class LoginAndOutServiceImpl implements ILoginAndOutService {

    @Value("${redis.cache.user.key}")
    private String userCacheKey;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    ICache cache;

    @Override
    public Result loginVarify(User user) {
        RegisterUser registerUser = userMapper.selectRegisterUser(user);
        if(null == registerUser || 9 == registerUser.getStatus()){
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"用户不存在");
        }
        String encrypt = MD5.encrypt(user.getPassword(), user.getUsername(), registerUser.getSalt());
        if(!registerUser.getPassword().equals(encrypt)){
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"密码不正确");
        }
        String token = TokenProccessor.makeToken();
        cache.setHalfHour(userCacheKey+ token,registerUser);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(registerUser,userDto);
        userDto.setToken(token);
        return ResultFactory.getDataResult(userDto);
    }

    @Override
    public Result logout(String token) {
        cache.remove(userCacheKey+token);
        return ResultFactory.getMessgaeResult(ResultEnum.SUCCESS,"退出成功");
    }
}
