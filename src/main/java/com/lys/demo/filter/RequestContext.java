package com.lys.demo.filter;

import com.lys.demo.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2020/12/23.
 */
public class RequestContext {

    public static HttpServletRequest getCurrentRequest() {
        // 通过`RequestContextHolder`获取当前request请求对象
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    public static User getCurrentUser() {
        // 通过request对象获取session对象，再获取当前用户对象
        return (User)getCurrentRequest().getSession().getAttribute("user");
    }

}
