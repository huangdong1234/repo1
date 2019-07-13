package cn.itcast.ssm.exception;

import cn.itcast.ssm.bean.ErrorCode;
import cn.itcast.ssm.bean.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 18:48
 */
@ControllerAdvice
public class ExceptionProcess {

    @ExceptionHandler
    @ResponseBody
    public Result handleException(Exception ex){
        ex.printStackTrace();
        return new Result(ErrorCode.FAIL);
    }

}
