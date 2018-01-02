package me.uplan.stillcoder.library.presenter;

/**
 * @descript:MVP模式中Presenter的基类接口
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */
public interface IPresenterLifeCycle {

    void onCreate();

    void onCreateView();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();
}
