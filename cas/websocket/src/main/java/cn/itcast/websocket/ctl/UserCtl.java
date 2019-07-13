package cn.itcast.websocket.ctl;

import cn.itcast.websocket.bean.ErrorCode;
import cn.itcast.websocket.bean.Result;
import cn.itcast.websocket.entity.User;
import cn.itcast.websocket.service.UserService;
import cn.itcast.websocket.util.JwtUtil;
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

    @RequestMapping("/user")
    @ResponseBody
    public Result getUser(Long userId) throws Exception {

        User user = userService.getUser(userId);
        if(true){
            throw new Exception();
        }
        return  new Result(user);
    }



    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws Exception {

        boolean bo=userService.login(username,password);
        if(bo){
            Map map=new HashMap();
            map.put("username", "username");
            map.put("timestamp", new Date().getTime());
            String token=JwtUtil.createJWT(map,JwtUtil.key);
            Cookie cookie=new Cookie("TOKEN",token);
            cookie.setMaxAge(30*60);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("hello");
            return "redirect:/view/index";
        }else{
            System.out.println("hello");
            return "login";
        }


    }

    @RequestMapping("/register")
    public String register(String username ,String password) throws Exception {
        userService.register(username,password);

        return  "login";
    }
}
