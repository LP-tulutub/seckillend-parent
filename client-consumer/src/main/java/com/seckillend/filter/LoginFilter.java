package com.seckillend.filter;

import com.seckillend.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter → servelet(网络接口) → interceptor → controller(方法) → interceptor → servelet → filter
 */
@Slf4j
@WebFilter(filterName = "RunFilterNO2", urlPatterns = {"/order/*","/message/*"})
public class LoginFilter implements Filter {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;

        //检查是否登录，用 redis 检查，没有登录重定向到登陆页面
        String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
        //log.warn("TT_TOKEN: " + token);
        if (token == null || token.equals("")) {
            //log.warn("转发: rep.sendRedirect → /index");
            rep.sendRedirect("/index");
            return;
        } else {
            Object obj = this.redisTemplate.opsForValue().get(token);
            if (obj == null) {
                rep.sendRedirect("/index");
                return;
            }
        }
        //log.warn("放行");
        //已登录放行
        filterChain.doFilter(req, rep);
    }

}
