package me.uplan.stillcoder.gayhub.ui.module.account;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.uplan.stillcoder.gayhub.GayHubApplication;
import me.uplan.stillcoder.gayhub.R;
import me.uplan.stillcoder.gayhub.common.util.ImageLoader;
import me.uplan.stillcoder.gayhub.contract.account.LoginContract;
import me.uplan.stillcoder.gayhub.di.ComponentInitialization;
import me.uplan.stillcoder.gayhub.di.component.AccountComponent;
import me.uplan.stillcoder.gayhub.di.component.DaggerAccountComponent;
import me.uplan.stillcoder.gayhub.di.module.AccountModule;
import me.uplan.stillcoder.gayhub.di.module.ActivityModule;
import me.uplan.stillcoder.gayhub.presenter.account.LoginPresenter;
import me.uplan.stillcoder.gayhub.ui.base.GayHubBaseLoadingActivity;
import me.uplan.stillcoder.library.presenter.IPresenterLifeCycle;

/**
 * Created by mac on 2018/1/7.
 */

public class LoginActivity extends GayHubBaseLoadingActivity implements LoginContract.ILoginView,
        ComponentInitialization<AccountComponent> {


    @BindView(R.id.iv_octoface)
    ImageView ivOctoface;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Inject
    LoginPresenter loginPresenter;


    @Override
    protected int setContainerId() {
        return R.layout.activity_login;
    }

    @Override
    protected IPresenterLifeCycle[] gatherPresenters() {
        return new IPresenterLifeCycle[]{loginPresenter};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initialize().inject(this);
        super.onCreate(savedInstanceState);
        ImageLoader.loadWithCircle(R.drawable.ic_octoface, ivOctoface);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        loginPresenter.login(username, password);
    }

    @Override
    protected String getLoadingMsg() {
        return "登陆中";
    }

    @Override
    public AccountComponent initialize() {
        return DaggerAccountComponent.builder().applicationComponent(GayHubApplication.get(this)
                .getComponent()).activityModule(new ActivityModule(this)).accountModule(new
                AccountModule()).build();
    }
}
