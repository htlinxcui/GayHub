package me.uplan.stillcoder.library.util;


import android.app.Activity;
import android.content.Context;
import android.support.v4.util.Preconditions;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @descript:异步事件流工具类
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class RxUtil {

    public static <T> ObservableTransformer<T, T> globalTransformer() {
        return globalTransformer(null);
    }

    /**
     * 执行全局的observable转换：
     * 1.在io线程订阅；
     * 2.在主线程观察；
     * 3.是否关联生命周期，若关联，则绑定生命周期
     *
     * @param provider 生命周期提供者
     * @return 转换器
     */
    public static <T> ObservableTransformer<T, T> globalTransformer(final LifecycleProvider provider) {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                if (null == provider) {
                    return observable;
                }
                return observable.compose(provider.<T>bindToLifecycle());
            }
        };
    }
}
