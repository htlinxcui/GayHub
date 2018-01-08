package me.uplan.stillcoder.gayhub.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import me.uplan.stillcoder.library.ui.base.lce.ILoadView;
import me.uplan.stillcoder.library.ui.widget.LoadingView;

/**
 * Created by mac on 2018/1/7.
 */

public abstract class GayHubBaseLoadingActivity extends GayHubBaseActivity implements ILoadView {
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingView = new LoadingView(this, getLoadingMsg());
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
        Snackbar.make(getWindow().getDecorView(), e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                .show();
    }

    protected abstract String getLoadingMsg();
}
