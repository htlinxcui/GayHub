package me.uplan.stillcoder.library.ui.base.lce;

import android.support.annotation.UiThread;

import me.uplan.stillcoder.library.ui.base.IView;

/**
 * Created by mac on 2018/1/7.
 */

public interface ILoadView extends IView {

    @UiThread
    void showLoading();

    @UiThread
    void dismissLoading();

    @UiThread
    void showError(Throwable e);
}
