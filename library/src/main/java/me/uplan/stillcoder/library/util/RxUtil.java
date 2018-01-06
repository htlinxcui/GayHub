package me.uplan.stillcoder.library.util;


import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.uplan.stillcoder.library.rx.function.ErrorAdaptFunction;
import me.uplan.stillcoder.library.rx.function.NegotiatedHandleFunction;
import me.uplan.stillcoder.library.rx.observer.BaseObserver;

/**
 * @descript:异步事件流工具类
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class RxUtil {

    public static <T> void generateNewObservable(@NonNull Observable originObservable,
                                                 BaseObserver observer) {
        originObservable.compose(RxUtil.globalTransformer()).subscribe(observer);
    }

    public static <T> ObservableTransformer<T,T> globalTransformer() {
        return globalTransformer(null);
    }

    /**
     * 执行全局的observable转换：
     * 1.在io线程订阅；
     * 2.在主线程观察；
     * 3.是否关联生命周期，若关联，则绑定生命周期
     * 4.异常处理
     *
     * @param provider 生命周期提供者
     * @return 转换器
     */
    public static <T> ObservableTransformer<T, T> globalTransformer(final LifecycleProvider
                                                                            provider) {
        final WeakReference<LifecycleProvider> providerWeakReference = new
                WeakReference<LifecycleProvider>(provider);

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable;

                LifecycleProvider provider = providerWeakReference.get();
                if (null != provider) {
                    observable = upstream.compose(provider.bindToLifecycle());
                } else {
                    observable = upstream;
                }

                return observable.map(new NegotiatedHandleFunction()).onErrorResumeNext(new
                        ErrorAdaptFunction<>()).subscribeOn(Schedulers.io()).observeOn
                        (AndroidSchedulers.mainThread());
            }
        };
    }
}
