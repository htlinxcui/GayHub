package me.uplan.stillcoder.library.view.fragment;

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
import me.uplan.stillcoder.library.presenter.IPresenter;

/**
 * @descript:Fragment基类，继承自RxFragment，以完成生命周期绑定
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public abstract class BaseFragment extends RxFragment {

    /**
     * presenter集合
     */
    private Set<IPresenter> presenters = new HashSet<>();
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IPresenter[] presenterArr = gatherPresenters();
        if (null == presenterArr) {
            return;
        }

        presenters.addAll(Arrays.asList(presenterArr));
        onPresenterLife(FragmentEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    protected abstract IPresenter[] gatherPresenters();

    /**
     * 将fragment的生命周期与presenter的生命周期关联
     *
     * @param event 生命周期
     */
    private void onPresenterLife(FragmentEvent event) {
        if (null == presenters) {
            return;
        }

        for (IPresenter presenter : presenters) {
            if (null == presenter) {
                continue;
            }

            switch (event) {
                case CREATE:
                    presenter.onCreate();
                case CREATE_VIEW:
                    presenter.onCreateView();
                case START:
                    presenter.onStart();
                case RESUME:
                    presenter.onResume();
                case PAUSE:
                    presenter.onPause();
                case STOP:
                    presenter.onStop();
                case DESTROY_VIEW:
                    presenter.onDestroyView();
                case DESTROY:
                    presenter.onDestroy();
            }
        }
    }
}
