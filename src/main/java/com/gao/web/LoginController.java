package com.gao.web;

import com.gao.dto.UserDto;
import com.gao.entity.User;
import com.gao.result.Result;
import com.gao.result.ResultEnum;
import com.gao.result.ResultFactory;
import com.gao.service.ILoginAndOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author gs
 * @Date created time 2019/4/30 15:50
 * @Description
 */
@Controller
public class LoginController {

    @Autowired
    private ILoginAndOutService loginAndOutService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user){
        if(StringUtils.isEmpty(user.getUsername())){
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"账号能为空");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return ResultFactory.getMessgaeResult(ResultEnum.PARAM_ERROR,"密码能为空");
        }
        Result result = loginAndOutService.loginVarify(user);
        return result;
    }

    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return loginAndOutService.logout(token);
    }

    @GetMapping("/noAuthority")
    @ResponseBody
    public Result noAuthority(){
        return ResultFactory.getDefaultResult(ResultEnum.UNAUTHORIZED);
    }

}
