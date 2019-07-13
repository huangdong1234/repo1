package cn.itcast.websocket.interceptor;

import cn.itcast.websocket.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if(requestURL.contains("login")||requestURL.contains("register")){
            return true;
        }



        //检查token是否存在
        Cookie token = WebUtils.getCookie(request, "TOKEN");
        if(token==null){
            //没有token表示未登录，则转向登录页面
            System.out.println(token);
          response.sendRedirect("/view/login");
          return false;
        }



        //校验token合法性
        String value = token.getValue();
        try{
            JwtUtil.parseJWT(value,JwtUtil.key);
        }catch (Exception e){
            e.printStackTrace();
            //token非法，则转向登录页面
            response.sendRedirect("/view/login");
            return false;
        }



        return true;

    }


}
