package me.uplan.stillcoder.gayhub.contract.account;

import me.uplan.stillcoder.library.ui.base.lce.ILoadView;

/**
 * Created by mac on 2018/1/7.
 */

public interface LoginContract {

    public interface ILoginView extends ILoadView {
    }

    public interface ILoginPresenter {
        void login(String username, String password);
    }
}
