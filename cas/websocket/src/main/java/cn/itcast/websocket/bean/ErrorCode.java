package cn.itcast.websocket.bean;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 18:57
 */
public enum ErrorCode {

    SUCCESS(0,"ok"),
    FAIL(99999,"系统未知异常");




    private Integer code;

    private String message;


    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
