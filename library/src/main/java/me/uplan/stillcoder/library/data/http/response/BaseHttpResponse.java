package me.uplan.stillcoder.library.data.http.response;

/**
 * @descript:http响应实体基础类
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class BaseHttpResponse<T> {
    private int code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return true;
    }

    @Override
    public String toString() {
        return "BaseHttpResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", me.uplan.stillcoder.gayhub.data=" + data +
                '}';
    }
}
