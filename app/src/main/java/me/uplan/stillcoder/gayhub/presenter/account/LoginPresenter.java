package me.uplan.stillcoder.gayhub.presenter.account;

import android.app.Application;
import android.text.TextUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.uplan.stillcoder.gayhub.contract.account.LoginContract;
import me.uplan.stillcoder.gayhub.data.http.api.AccountApi;
import me.uplan.stillcoder.gayhub.data.model.User;
import me.uplan.stillcoder.gayhub.data.sp.AccountSP;
import me.uplan.stillcoder.library.data.http.exception.ApiException;
import me.uplan.stillcoder.library.presenter.SimplePresenter;
import me.uplan.stillcoder.library.rx.observer.BaseObserver;
import me.uplan.stillcoder.library.util.RxUtil;

/**
 * Created by mac on 2018/1/7.
 */

public class LoginPresenter extends SimplePresenter<LoginContract.ILoginView> implements
        LoginContract.ILoginPresenter {
    private final AccountApi mAccountApi;

    @Inject
    Application mContext;

    @Inject
    public LoginPresenter(AccountApi api) {
        mAccountApi = api;
    }


    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            viewRef.get().showError(new Throwable("请输入用户名"));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            viewRef.get().showError(new Throwable("请输入密码"));
            return;
        }

        BaseObserver<User> observer = new BaseObserver<User>() {
            @Override
            protected void onStart(Disposable d) {
                viewRef.get().showLoading();
            }

            @Override
            protected void onSuccess(User user) {
                AccountSP.saveLogonUser(mContext, user);
                viewRef.get().dismissLoading();
            }

            @Override
            protected void onError(ApiException e) {
                viewRef.get().showError(e);
                viewRef.get().dismissLoading();
            }
        };

        mAccountApi.login(username, password).compose(RxUtil.globalTransformer(providerRef.get())
        ).subscribe(observer);
    }
}
