package me.uplan.stillcoder.library.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.uplan.stillcoder.library.presenter.IPresenterLifeCycle;

/**
 * @descript:Fragment基类，继承自RxFragment，以完成生命周期绑定
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public abstract class BaseFragment extends RxFragment implements IView {

    /**
     * presenter集合
     */
    private Set<IPresenterLifeCycle> presenters = new HashSet<>();
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IPresenterLifeCycle[] presenterArr = gatherPresenters();
        if (null == presenterArr) {
            return;
        }

        presenters.addAll(Arrays.asList(presenterArr));
        onPresenterLife(FragmentEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        onPresenterLife(FragmentEvent.CREATE_VIEW);
        View parent = inflater.inflate(setContainerId(), container, false);
        unbinder = ButterKnife.bind(this, parent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        onPresenterLife(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        onPresenterLife(FragmentEvent.RESUME);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onPresenterLife(FragmentEvent.DESTROY_VIEW);

        if (null != unbinder) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onPresenterLife(FragmentEvent.DESTROY);
    }

    /**
     * 设置fragment内容布局id
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
    private void onPresenterLife(FragmentEvent event) {
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
                case CREATE_VIEW:
                    presenter.onCreateView();
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
                case DESTROY_VIEW:
                    presenter.onDestroyView();
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
