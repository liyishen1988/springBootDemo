package com.lys.demo.filter;

import com.lys.demo.entity.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2020/12/23.
 */
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 简单的白名单，登录这个接口直接放行
        if ("/login".equals(request.getRequestURI())) {
            //关于chain.doFilter(request,response):他的作用是将请求转发给过滤器链上下一个对象。这里的下一个指的是下一个filter，如果没有filter那就是你请求的资源。
            filterChain.doFilter(request, response);
            return;
        }

        // 已登录就放行
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 走到这里就代表是其他接口，且没有登录，设置响应数据类型为json（前后端分离）
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 设置响应内容，结束请求
        out.write("请先登录");
        out.flush();
        out.close();

    }
}
