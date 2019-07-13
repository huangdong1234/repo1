package cn.itcast.websocket.ctl;

import cn.itcast.websocket.bean.ErrorCode;
import cn.itcast.websocket.bean.Result;
import cn.itcast.websocket.entity.User;
import cn.itcast.websocket.websocket.WebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/6/13 20:44
 */
@Controller
@RequestMapping("/weixin")
public class PayController {


    @RequestMapping("/pay")
    @ResponseBody
    public Result getUser(String transactionId) throws Exception {

        WebSocket.session.getBasicRemote().sendText("支付成功");

        return  new Result(ErrorCode.SUCCESS);
    }
}
