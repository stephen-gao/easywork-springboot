package com.gao.interceptor;

import com.gao.cache.ICache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author gs
 * @Date created time 2019/4/30 18:31
 * @Description
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Value("${redis.cache.user.key}")
    private String userCacheKey;

    @Autowired
    ICache cache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.warn("AuthorityInterceptor拦截器");

        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            Object o = cache.get(userCacheKey + token);
            if(null != o){
                logger.warn("有权限");
                return true;
            }
        }
        logger.warn("无权限");
        response.sendRedirect("/easywork/noAuthority");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.warn("AuthorityInterceptor拦截器 after");
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            cache.expireHalfHour(userCacheKey+token);
        }
    }
}
