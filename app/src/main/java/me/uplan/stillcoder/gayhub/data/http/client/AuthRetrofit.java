package me.uplan.stillcoder.gayhub.data.http.client;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import me.uplan.stillcoder.gayhub.common.constants.ApiConstants;
import me.uplan.stillcoder.library.data.http.base.ApiEndPoint;
import me.uplan.stillcoder.library.data.http.base.BaseOkHttpClient;
import me.uplan.stillcoder.library.data.http.base.BaseRetrofit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2018/1/6.
 */

public class AuthRetrofit extends BaseRetrofit {

    private String username;
    private String password;

    @Inject
    public AuthRetrofit() {
    }

    @Override
    public ApiEndPoint getApiEndPoint() {
        return new ApiEndPoint() {
            @Override
            public String getEndPoint() {
                return ApiConstants.BASE_URL;
            }
        };
    }

    public void setAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public OkHttpClient getOkhttpClient() {
        return new AuthHttpClient(username, password).get();
    }

    private class AuthHttpClient extends BaseOkHttpClient {

        private String username;
        private String password;

        public AuthHttpClient(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public OkHttpClient.Builder customBuild(OkHttpClient.Builder builder) {
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String userCredentials = username + ":" + password;
                        String basicAuth =
                                "Basic " + new String(Base64.encode(userCredentials.getBytes(),
                                        Base64.DEFAULT));

                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", basicAuth.trim());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
            }
            return builder;
        }
    }
}
