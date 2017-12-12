package me.uplan.stillcoder.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @descript:Activity基类，继承RxAppCompatActivity，以完成生命周期绑定
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setContainerId() > 0) {
            setContentView(setContainerId());
        }
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != unbinder) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    /**
     * 设置activity的内容布局id，当activity无内容时，id可以为0
     *
     * @return 对应的布局id
     */
    protected abstract int setContainerId();
}
