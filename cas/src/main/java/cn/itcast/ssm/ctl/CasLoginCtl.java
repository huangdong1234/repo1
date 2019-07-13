package cn.itcast.ssm.ctl;

import cn.itcast.ssm.bean.ErrorCode;
import cn.itcast.ssm.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/6/11 13:06
 */
@Controller
@RequestMapping("/cas")
public class CasLoginCtl {

    Map serviceTicketMap=new HashMap();


    @RequestMapping("/login")
    public String login(HttpServletRequest request,String redirectUrl) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if(userId==null){//未登录
            session.setAttribute("redirectUrl",redirectUrl);//保存跳转url
            return "/login";
        }else{

            String st= UUID.randomUUID().toString();
            serviceTicketMap.put(st,userId);//这里缺少设置有效时间
            session.removeAttribute("redirectUrl");
            return "redirect:"+redirectUrl+"?st="+st;
        }

    }

    @RequestMapping("/validate")
    @ResponseBody
    public Result validate(HttpServletRequest request,String st) throws Exception {

        if(st==null){
            return new Result(ErrorCode.INVALID_TICKET);
        }
        String userId = (String) serviceTicketMap.get(st);
        if(userId==null){
            serviceTicketMap.remove(st);
            return new Result(ErrorCode.INVALID_TICKET);
        }

        return new Result(ErrorCode.SUCCESS,userId);
    }



 }
