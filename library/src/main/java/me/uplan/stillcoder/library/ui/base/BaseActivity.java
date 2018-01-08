package me.uplan.stillcoder.library.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.uplan.stillcoder.library.presenter.IPresenterLifeCycle;

/**
 * @descript:Activity基类，继承RxAppCompatActivity，以完成生命周期绑定
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public abstract class BaseActivity extends RxAppCompatActivity implements IView {

    private Set<IPresenterLifeCycle> presenters = new HashSet<>();
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setContainerId() > 0) {
            setContentView(setContainerId());
        }

        IPresenterLifeCycle[] presenterArr = gatherPresenters();
        if (null == presenterArr) {
            return;
        }

        presenters.addAll(Arrays.asList(presenterArr));
        onPresenterLife(ActivityEvent.CREATE);

        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onPresenterLife(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onPresenterLife(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPresenterLife(ActivityEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        onPresenterLife(ActivityEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onPresenterLife(ActivityEvent.DESTROY);

        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    /**
     * 设置activity的内容布局id，当activity无内容时，id可以为0
     *
     * @return 对应的布局id
     */
    protected abstract int setContainerId();

    /**
     * 获取每个fragment的presenter集合，方便集中管理
     *
     * @return presenter集合
     */
    protected abstract IPresenterLifeCycle[] gatherPresenters();

    /**
     * 将fragment的生命周期与presenter的生命周期关联
     *
     * @param event 生命周期
     */
    private void onPresenterLife(ActivityEvent event) {
        if (null == presenters) {
            return;
        }

        for (IPresenterLifeCycle presenter : presenters) {
            if (null == presenter) {
                continue;
            }

            switch (event) {
                case CREATE:
                    presenter.onCreate(this, this);
                    break;
                case START:
                    presenter.onStart();
                    break;
                case RESUME:
                    presenter.onResume();
                    break;
                case PAUSE:
                    presenter.onPause();
                    break;
                case STOP:
                    presenter.onStop();
                    break;
                case DESTROY:
                    presenter.onDestroy();
                    break;
                default:
                    break;
            }
        }
    }
}
