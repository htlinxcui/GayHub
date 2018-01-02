package me.uplan.stillcoder.library.model.http.exception;


import android.support.annotation.NonNull;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @descript:Retrofit异常错误适配器，转换成程序需要的ApiException
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class ExceptionAdapter {

    public enum Error {

        ERROR_UNKNOWN(10000, "未知异常"), ERROR_HTTP_CLIENT(10001, "客户端http异常"),
        ERROR_HTTP_SERVER(10002, "服务端http异常"), ERROR_NEGOTIATED(10003, "两端协商好的异常"),
        ERROR_PARSE_DATA(10004, "解析数据异常"), ERROR_UNKNOWN_HOST(10005, "未知主机异常"),
        ERROR_CONNECT(10006, "服务器拒绝连接或未在指定的端口上监听"), ERROR_CONNECT_TIMEOUT(10007,
                "连接超时"), ERROR_SOCKET_TIMEOUT(10008, "等待服务器响应超时");

        private int code;
        private String msg;

        private Error(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public static ApiException adapt(Throwable e) {
        ApiException exception = null;

        if (e instanceof HttpException) {
            HttpException ex = (HttpException) e;
            int code = ex.code();
            if (code >= 400 && code < 500) {
                exception = new ApiException(e, Error.ERROR_HTTP_CLIENT.code);
                exception.setMsg(Error.ERROR_HTTP_CLIENT.msg);
            } else if (code >= 500) {
                exception = new ApiException(e, Error.ERROR_HTTP_SERVER.code);
                exception.setMsg(Error.ERROR_HTTP_SERVER.msg);
            }
        } else if (e instanceof NegotiatedException) {
            NegotiatedException ex = (NegotiatedException) e;
            exception = new ApiException(e, Error.ERROR_NEGOTIATED.code);
            exception.setMsg(Error.ERROR_NEGOTIATED.msg);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            exception = new ApiException(e, Error.ERROR_PARSE_DATA.code);
            exception.setMsg(Error.ERROR_PARSE_DATA.msg);
        } else if (e instanceof UnknownHostException) {
            exception = new ApiException(e, Error.ERROR_UNKNOWN_HOST.code);
            exception.setMsg(Error.ERROR_UNKNOWN_HOST.msg);
        } else if (e instanceof ConnectException) {
            exception = new ApiException(e, Error.ERROR_CONNECT.code);
            exception.setMsg(Error.ERROR_CONNECT.msg);
        } else if (e instanceof ConnectTimeoutException) {
            exception = new ApiException(e, Error.ERROR_CONNECT_TIMEOUT.code);
            exception.setMsg(Error.ERROR_CONNECT_TIMEOUT.msg);
        } else if (e instanceof SocketTimeoutException) {
            exception = new ApiException(e, Error.ERROR_SOCKET_TIMEOUT.code);
            exception.setMsg(Error.ERROR_SOCKET_TIMEOUT.msg);
        } else {
            exception = new ApiException(e, Error.ERROR_UNKNOWN.code);
            exception.setMsg(Error.ERROR_UNKNOWN.msg);
        }

        return exception;
    }
}
