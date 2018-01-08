package me.uplan.stillcoder.library.presenter;

import android.support.annotation.UiThread;

import com.trello.rxlifecycle2.LifecycleProvider;

import me.uplan.stillcoder.library.ui.base.IView;

/**
 * @descript:MVP模式中Presenter的基类接口
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */
public interface IPresenterLifeCycle {

    @UiThread
    void onCreate(IView v, LifecycleProvider p);

    @UiThread
    void onCreateView();

    @UiThread
    void onStart();

    @UiThread
    void onResume();

    @UiThread
    void onPause();

    @UiThread
    void onStop();

    @UiThread
    void onDestroyView();

    @UiThread
    void onDestroy();
}
