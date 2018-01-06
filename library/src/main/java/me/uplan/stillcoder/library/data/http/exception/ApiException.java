package me.uplan.stillcoder.library.data.http.exception;

/**
 * @descript:Api接口错误/异常封装类，只包含错误码和错误信息
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class ApiException extends Exception {
    private int code;
    private String msg;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
