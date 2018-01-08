package me.uplan.stillcoder.library.ui.base.lce;

import android.support.annotation.UiThread;

/**
 * Created by mac on 2018/1/7.
 */

public interface ILceView<T> extends ILoadView {
    @UiThread
    void showContent(T data);

    @UiThread
    void showEmpty();
}
