package me.uplan.stillcoder.library.model.http.function;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import me.uplan.stillcoder.library.model.http.exception.ExceptionAdapter;

/**
 * @descript:http错误适配功能类
 * @auther:stillcoder
 * @date:2017/12/13
 * @todoDescript:
 */

public class ErrorAdaptFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        return Observable.error(ExceptionAdapter.adapt(throwable));
    }
}
