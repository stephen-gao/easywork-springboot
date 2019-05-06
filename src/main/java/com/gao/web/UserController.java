package com.gao.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.base.BaseController;
import com.gao.base.BaseVO;
import com.gao.entity.RegisterUser;
import com.gao.entity.User;
import com.gao.result.Result;
import com.gao.result.ResultEnum;
import com.gao.result.ResultFactory;
import com.gao.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author gaosheng
 * @since 2019-04-29
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @GetMapping("/userPage")
    @ResponseBody
    public Result getUserPage(User user, BaseVO vo){
        logger.info("查询用户列表参数: [{}], 分页信息: [{}]",JSON.toJSONString(user),JSON.toJSONString(vo));
        Page<User> page = new Page<>();
        page.setCurrent(vo.getCurrent());
        page.setSize(vo.getSize());
        setPageSort(page,vo);
        return ResultFactory.getDataResult(userService.getUserPage(page,user));
    }

    @PostMapping("/add")
    @ResponseBody
    public Result addUser(@RequestBody RegisterUser user){

        logger.info(JSON.toJSONString(user));
        if(StringUtils.isEmpty(user.getUsername())){
            logger.info("新建用户=>用户名不能为空: [{}]",JSON.toJSONString(user));
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"用户名不能为空");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            logger.info("新建用户=>[{}] 密码不能为空: [{}]",user.getUsername(),JSON.toJSONString(user));
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"密码不能为空");
        }
        if(userService.varifyUsernameExist(user)){
            logger.info("新建用户=>[{}] 不能为空: [{}]",user.getUsername(),JSON.toJSONString(user));
            return ResultFactory.getMessgaeResult(ResultEnum.FAIL,"用户名已存在");
        }
        userService.addUser(user);
        return ResultFactory.getDefaultResult(ResultEnum.SUCCESS);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateUser(@RequestBody User user){
        //用户名查重
        if(!StringUtils.isEmpty(user.getUsername())){
            if(userService.varifyUsernameExist(user)){
                logger.info("更新用户=>[{}] 用户名已存在: [{}]",user.getId(),JSON.toJSONString(user));
                return ResultFactory.getMessgaeResult(ResultEnum.FAIL,"用户名已存在");
            }
        }
        userService.updateUserByUserId(user);
        return ResultFactory.getDefaultResult(ResultEnum.SUCCESS);
    }

    @Cacheable(value = "user", key="#id")
    @GetMapping("/user/{id}")
    @ResponseBody
    public Result getUser(@PathVariable("id") String id){
        logger.info("查询用户=>[{}]",id);
        User user = userService.getById(id);
        if(null == user){
            logger.info("查询用户=>[{}] 用户不存在",id);
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"用户不存在");
        }
        logger.info("查询用户=>[{}] 信息成功: [{}]",id,JSON.toJSONString(user));
        return ResultFactory.getDataResult(user);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable("id") String id){
        logger.info("删除用户=>[{}]",id);
        User user = userService.getById(id);
        if(null == user){
            logger.info("删除用户=>[{}] 用户不存在",id);
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"用户不存在");
        }
        user.setStatus(9);
        userService.updateById(user);
        logger.info("删除用户=>[{}] 信息成功: [{}]",id,JSON.toJSONString(user));
        return ResultFactory.getDefaultResult(ResultEnum.SUCCESS);
    }
}
