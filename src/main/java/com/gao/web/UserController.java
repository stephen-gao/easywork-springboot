package com.gao.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.base.BaseController;
import com.gao.base.BaseVO;
import com.gao.entity.User;
import com.gao.result.Result;
import com.gao.result.ResultFactory;
import com.gao.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        logger.info(JSON.toJSONString(user));
        Page<User> page = new Page<>();
        page.setCurrent(vo.getCurrent());
        page.setSize(vo.getSize());
        setPageSort(page,vo);
        return ResultFactory.getSuccessResult(userService.getUserPage(page,user));
    }

    @PostMapping("/add")
    @ResponseBody
    public String addUser(@RequestBody User user){
//        if(StringUtils.isEmpty(user.getId())){
//            return "bbb";
//        }
        logger.info(JSON.toJSONString(user));
        if(StringUtils.isEmpty(user.getUsername())){
            return "ccc";
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return "ddd";
        }
        Date date = new Date();
        user.setCreateTime(date);
        user.setEditTime(date);
        userService.save(user);
        return "aaa";
    }

    @PostMapping("/add2")
    @ResponseBody
    public String addUser2(User user){

        logger.info(JSON.toJSONString(user));
        if(StringUtils.isEmpty(user.getUsername())){
            return "fff";
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return "eee";
        }
        userService.save(user);
        return "aaa";
    }
}
