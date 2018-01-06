package me.uplan.stillcoder.gayhub.data.http.client;

import android.app.Application;

import java.io.IOException;

import javax.inject.Inject;

import me.uplan.stillcoder.gayhub.data.sp.AccountSP;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2018/1/6.
 */

public class GeneralHttpClient extends CacheHttpClient {
    @Inject
    Application mContext;

    @Inject
    public GeneralHttpClient() {
    }

    public String getAcceptHeader() {
        return "application/vnd.github.v3+json";
    }

    public String getUserAgentHeander() {
        return "GayHub";
    }

    @Override
    public OkHttpClient.Builder customBuild(OkHttpClient.Builder builder) {
        builder = super.customBuild(builder);

        return super.customBuild(builder);
    }

    private final Interceptor mGeneralInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            Request.Builder newBuilder = oldRequest.newBuilder().header("Accept", getAcceptHeader
                    ()).header("User-Agent", getUserAgentHeander());

            if(AccountSP.hasLogin(mContext)){
                newBuilder
                        .header("Authorization", "token " + AccountSP.getLogonToken(mContext));
            }

            return chain.proceed(newBuilder.build());
        }
    };
}
