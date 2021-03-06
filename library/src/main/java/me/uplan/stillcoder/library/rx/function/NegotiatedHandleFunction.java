package me.uplan.stillcoder.library.rx.function;


import android.support.annotation.NonNull;

import io.reactivex.functions.Function;
import me.uplan.stillcoder.library.data.http.exception.NegotiatedException;
import me.uplan.stillcoder.library.data.http.response.BaseHttpResponse;

/**
 * @descript:协商好的异常的处理功能类，具体的处理实现由用户自定义
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class NegotiatedHandleFunction<T> implements Function<BaseHttpResponse<T>, T> {
    @Override
    public T apply(@NonNull BaseHttpResponse<T> response) throws Exception {
        if (!response.isSuccess()) {
            throw new NegotiatedException(response.getCode(), response.getMessage());
        }

        return response.getData();
    }
}
