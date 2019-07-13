package cn.itcast.ssm.interceptor;

import cn.itcast.ssm.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/31 9:13
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String requestURL = request.getRequestURL().toString();

        //登录和注册的相关页面和接口都放行
        if(requestURL.contains("login")||requestURL.contains("register")||requestURL.contains("cas/validate")){
            return true;
        }



        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if(userId==null){
            response.sendRedirect("/view/login");
            return false;
        }


        return true;

    }


}
