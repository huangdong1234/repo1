package cn.itcast.qqmusic.ctl;

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


    @RequestMapping("/mymusic")
    public String viewLogin(){

        return "mymusic";
    }


}
