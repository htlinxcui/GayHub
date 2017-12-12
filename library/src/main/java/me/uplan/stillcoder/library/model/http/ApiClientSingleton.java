package me.uplan.stillcoder.library.model.http;

import com.trello.rxlifecycle2.internal.Preconditions;

import me.uplan.stillcoder.library.BuildConfig;
import me.uplan.stillcoder.library.model.http.configuration.ApiConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @descript:http通信客户端，单例实现
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class ApiClientSingleton {
    private Retrofit retrofit;
    private ApiConfiguration configuration;

    private ApiClientSingleton() {

    }

    public static final ApiClientSingleton getInstance() {
        return SingletonHolder.singleton;
    }

    public synchronized void init(ApiConfiguration apiConfiguration) {
        Preconditions.checkNotNull(apiConfiguration, "configuration==null");
        if (null != retrofit) {
            return;
        }

        configuration = apiConfiguration;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(httpLoggingInterceptor);
        }

        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder().baseUrl(configuration.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).build();
    }

    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private static class SingletonHolder {
        private static final ApiClientSingleton singleton = new ApiClientSingleton();
    }
}
