package me.uplan.stillcoder.gayhub.data.http.datasource;

import android.app.Application;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import me.uplan.stillcoder.gayhub.common.constants.ApiConstants;
import me.uplan.stillcoder.gayhub.data.http.api.AccountApi;
import me.uplan.stillcoder.gayhub.data.http.client.AuthRetrofit;
import me.uplan.stillcoder.gayhub.data.http.request.AuthorizationParam;
import me.uplan.stillcoder.gayhub.data.http.response.AuthorizationResponse;
import me.uplan.stillcoder.gayhub.data.http.service.AccountService;
import me.uplan.stillcoder.gayhub.data.model.User;
import me.uplan.stillcoder.gayhub.data.sp.AccountSP;

/**
 * Created by mac on 2018/1/6.
 */

public class AccountDataSource implements AccountApi {

    @Inject
    Application mApplication;

    @Inject
    AuthRetrofit mAuthRetrofit;

    @Inject
    public AccountDataSource() {

    }

    @Override
    public Observable<User> login(String username, String password) {
        mAuthRetrofit.setAuth(username, password);
        AccountService accountService = mAuthRetrofit.get().create(AccountService.class);

        AuthorizationParam param = new AuthorizationParam();
        param.scopes = ApiConstants.SCOPES;
        param.note = ApiConstants.NOTE;
        param.client_id = ApiConstants.CLIENT_ID;
        param.client_secret = ApiConstants.CLIENT_SECRET;

        return accountService.createAuthorization(param).flatMap(new Function<AuthorizationResponse,
                ObservableSource<User>>() {


            @Override
            public ObservableSource<User> apply(AuthorizationResponse authorizationResponse) throws
                    Exception {
                String token = authorizationResponse.getToken();
                AccountSP.saveLogonToken(mApplication, token);
                return accountService.getUserInfo(token);
            }
        });
    }
}
