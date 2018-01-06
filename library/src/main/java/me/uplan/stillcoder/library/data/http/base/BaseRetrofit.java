package me.uplan.stillcoder.library.data.http.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 2018/1/6.
 */

public abstract class BaseRetrofit {

    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(getApiEndPoint().getEndPoint())
                .client(getOkhttpClient()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

    public abstract ApiEndPoint getApiEndPoint();

    public abstract OkHttpClient getOkhttpClient();
}
