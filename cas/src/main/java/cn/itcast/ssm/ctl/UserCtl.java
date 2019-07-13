package cn.itcast.ssm.ctl;

import cn.itcast.ssm.bean.ErrorCode;
import cn.itcast.ssm.bean.Result;
import cn.itcast.ssm.entity.User;
import cn.itcast.ssm.service.UserService;
import cn.itcast.ssm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 16:23
 */
@Controller
@RequestMapping("/user")
public class UserCtl {
    private static final Logger logger = LoggerFactory.getLogger(UserCtl.class);
    @Autowired
    private UserService userService;




    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws Exception {

        boolean bo=userService.login(username,password);//登录认证

        if(bo){//登录成功
            HttpSession session = request.getSession();
            session.setAttribute("userId",username);
            System.out.println("登录成功");

            String redirectUrl = (String) session.getAttribute("redirectUrl");

            if(redirectUrl!=null){
                return "redirect:/cas/login?redirectUrl="+redirectUrl;
            }else{
                return "redirect:/view/index";
            }


        }else{ //登录失败
            System.out.println("登录shibai ");
            return "login";
        }

    }

    @RequestMapping("/register")
    public String register(String username ,String password) throws Exception {
        userService.register(username,password);

        return  "login";
    }
}
