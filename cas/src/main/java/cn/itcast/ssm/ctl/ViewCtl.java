package cn.itcast.ssm.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/31 9:03
 */
@Controller
@RequestMapping("/view")
public class ViewCtl {


    @RequestMapping("/login")
    public String viewLogin(){

        return "login";
    }

    @RequestMapping("/register")
    public String viewRegister(){

        return "register";
    }
    @RequestMapping("/index")
    public String viewindex(){

        return "index";
    }
}
