package me.uplan.stillcoder.library.data.http.base;

import java.util.concurrent.TimeUnit;

import me.uplan.stillcoder.library.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by mac on 2018/1/6.
 */

public abstract class BaseOkHttpClient {
    private static final long CONNECT_TIMEOUT = 15 * 1000;

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT,
                TimeUnit.MILLISECONDS).addInterceptor(new HttpLoggingInterceptor().setLevel
                (BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor
                        .Level.NONE));
        builder = customBuild(builder);

        return builder.build();
    }

    public abstract OkHttpClient.Builder customBuild(OkHttpClient.Builder builder);
}
