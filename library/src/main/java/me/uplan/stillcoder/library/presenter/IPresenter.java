package me.uplan.stillcoder.library.presenter;

import android.support.annotation.UiThread;

import com.trello.rxlifecycle2.LifecycleProvider;

import me.uplan.stillcoder.library.ui.base.IView;

/**
 * Created by mac on 2018/1/7.
 */

public interface IPresenter<V extends IView> {
    @UiThread
    void attachViewWithLifeCycle(V view, LifecycleProvider provider);

    @UiThread
    void detachViewWithLifeCycle();
}
