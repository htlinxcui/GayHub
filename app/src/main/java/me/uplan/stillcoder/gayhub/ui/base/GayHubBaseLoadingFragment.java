package me.uplan.stillcoder.gayhub.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.uplan.stillcoder.library.ui.base.BaseFragment;
import me.uplan.stillcoder.library.ui.base.lce.ILoadView;
import me.uplan.stillcoder.library.ui.widget.LoadingView;

/**
 * Created by mac on 2018/1/7.
 */

public abstract class GayHubBaseLoadingFragment extends BaseFragment implements ILoadView {
    private LoadingView mLoadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingView=new LoadingView(getActivity(),getLoadingMsg());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showLoading() {
        mLoadingView.show();
    }

    @Override
    public void dismissLoading() {
        mLoadingView.dismiss();
    }

    @Override
    public void showError(Throwable e) {
        Snackbar.make(getActivity().getWindow().getDecorView(), e.getLocalizedMessage(), Snackbar
                .LENGTH_LONG)
                .show();
    }

    protected abstract String getLoadingMsg();
}
