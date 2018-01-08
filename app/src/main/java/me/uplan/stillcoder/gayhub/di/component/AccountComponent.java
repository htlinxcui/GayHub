package me.uplan.stillcoder.gayhub.di.component;

import dagger.Component;
import me.uplan.stillcoder.gayhub.di.annotation.PerActivity;
import me.uplan.stillcoder.gayhub.di.module.AccountModule;
import me.uplan.stillcoder.gayhub.di.module.ActivityModule;
import me.uplan.stillcoder.gayhub.ui.module.account.LoginActivity;

/**
 * Created by mac on 2018/1/7.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,
        AccountModule.class})
public interface AccountComponent extends ActivityComponent {
    void inject(LoginActivity loginActivity);
}
