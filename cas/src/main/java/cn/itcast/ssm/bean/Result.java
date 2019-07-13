package cn.itcast.ssm.bean;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 18:56
 */
public class Result {


    private Integer ret;
    private String message;
    private  Object data;

    public Result(ErrorCode code){
        ret=code.getCode();
        message=code.getMessage();
    }
    public Result(Object data) {
        this.data = data;
        ret=ErrorCode.SUCCESS.getCode();
        message=ErrorCode.SUCCESS.getMessage();
    }

    public Result(ErrorCode code,Object data) {
        this.data = data;
        ret=code.getCode();
        message=code.getMessage();
    }



    public Integer getRet() {
        return ret;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
