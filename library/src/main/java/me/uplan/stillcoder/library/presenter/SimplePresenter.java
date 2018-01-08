package me.uplan.stillcoder.library.presenter;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.lang.ref.SoftReference;

import me.uplan.stillcoder.library.ui.base.IView;

/**
 * @descript:IPresenter接口的空实现，所有Presenter务必继承该类。该类持有view的弱引用，避免内存泄漏。
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class SimplePresenter<V extends IView> implements IPresenter, IPresenterLifeCycle {

    protected SoftReference<V> viewRef;
    protected SoftReference<LifecycleProvider> providerRef;

    @Override
    public void attachViewWithLifeCycle(IView view, LifecycleProvider provider) {
        Preconditions.checkNotNull(view, "view==null");
        viewRef = new SoftReference(view);
        providerRef = new SoftReference<LifecycleProvider>(provider);
    }

    @Override
    public void detachViewWithLifeCycle() {
        if (null != viewRef && null != viewRef.get()) {
            viewRef.clear();
            viewRef = null;
        }

        if (null != providerRef && null != providerRef.get()) {
            providerRef.clear();
            providerRef = null;
        }
    }

    @Override
    public void onCreate(IView v, LifecycleProvider p) {
        attachViewWithLifeCycle(v, p);
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {
        detachViewWithLifeCycle();
    }
}
