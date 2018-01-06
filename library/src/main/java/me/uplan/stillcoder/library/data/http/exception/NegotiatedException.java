package me.uplan.stillcoder.library.data.http.exception;

/**
 * @descript :客户端与服务器约定好的异常，如用户名错误、密码错误、登录过期等，约定的异常码必须与已知的http响应码错开，比如定义成负数
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class NegotiatedException extends RuntimeException {
    private int code;
    private String msg;

    public NegotiatedException(int code, String msg) {
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
