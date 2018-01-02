package me.uplan.stillcoder.library.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.lang.ref.WeakReference;

import me.uplan.stillcoder.library.view.IView;

/**
 * @descript:IPresenter接口的空实现，所有Presenter务必继承该类。该类持有view的弱引用，避免内存泄漏。
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class SimplePresenter<V extends IView> implements IPresenterLifeCycle {

    protected WeakReference<V> viewRef;
    protected WeakReference<LifecycleProvider> providerRef;

    public SimplePresenter(@NonNull V view, @Nullable LifecycleProvider provider) {
        Preconditions.checkNotNull(view, "view==null");
        viewRef = new WeakReference<V>(view);
        providerRef = new WeakReference<LifecycleProvider>(provider);
    }

    @Override
    public void onCreate() {

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
        clear();
    }

    @Override
    public void onDestroy() {
        clear();
    }

    private void clear() {
        if (null != viewRef && null != viewRef.get()) {
            viewRef.clear();
            viewRef = null;
        }

        if (null != providerRef && null != providerRef.get()) {
            providerRef.clear();
            providerRef = null;
        }
    }
}
