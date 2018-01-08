package me.uplan.stillcoder.gayhub.di.module;

import dagger.Module;
import dagger.Provides;
import me.uplan.stillcoder.gayhub.contract.account.LoginContract;
import me.uplan.stillcoder.gayhub.data.http.api.AccountApi;
import me.uplan.stillcoder.gayhub.data.http.datasource.AccountDataSource;
import me.uplan.stillcoder.gayhub.ui.module.account.LoginActivity;

/**
 * Created by mac on 2018/1/7.
 */

@Module
public class AccountModule {

    @Provides
    public AccountApi provideAccountApi(AccountDataSource dataSource){
        return dataSource;
    }
}
