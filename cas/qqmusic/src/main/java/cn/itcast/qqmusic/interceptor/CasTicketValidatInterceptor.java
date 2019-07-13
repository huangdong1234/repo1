package cn.itcast.qqmusic.interceptor;

import cn.itcast.qqmusic.bean.Result;
import cn.itcast.qqmusic.util.HttpUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class CasTicketValidatInterceptor extends HandlerInterceptorAdapter {

//casTicketValidatInterceptor
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("HandlerInterceptorAdapter");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if(userId==null){//未登录
            System.out.println("userId=null 未登录！");
            String st = request.getParameter("st");
            if(st==null){
                System.out.println("st=null 未登录！");
                response.sendRedirect("http://localhost:8080/cas/login?redirectUrl="+request.getRequestURL());
                return false;

            }else{
                //验证st合法性
                String resultStr = HttpUtil.get("http://localhost:8080/cas/validate?st=" + st);
                System.out.println("result："+resultStr);
                ObjectMapper objectMapper=new ObjectMapper();
                Result result = objectMapper.readValue(resultStr, Result.class);

                if(result.getRet().equals(0)){//验证有效
                    userId = (String) result.getData();
                    request.getSession().setAttribute("userId",userId);
                    return true;
                }else{
                    response.sendRedirect("http://localhost:8080/cas/login?redirectUrl="+request.getRequestURL());
                    return false;
                }
            }

        }



        return true;

    }


}
