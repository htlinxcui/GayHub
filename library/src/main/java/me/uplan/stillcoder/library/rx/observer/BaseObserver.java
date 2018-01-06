package me.uplan.stillcoder.library.rx.observer;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.uplan.stillcoder.library.data.http.exception.ApiException;

/**
 * @descript:对Observer进行中转
 * @auther:stillcoder
 * @date:2017/12/14
 * @todoDescript:
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onError((ApiException) e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onStart(Disposable d);

    protected abstract void onSuccess(T response);

    protected abstract void onError(ApiException e);
}
