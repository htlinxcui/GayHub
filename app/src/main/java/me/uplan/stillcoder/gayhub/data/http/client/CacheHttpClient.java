package me.uplan.stillcoder.gayhub.data.http.client;

import android.app.Application;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import me.uplan.stillcoder.library.data.http.base.BaseOkHttpClient;
import me.uplan.stillcoder.library.util.NetworkUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2018/1/6.
 */

public class CacheHttpClient extends BaseOkHttpClient {
    private static final long CACHE_SIZE = 50 * 1024 * 1024;
    private static final String CACHE_DIR = "github_repo";

    @Inject
    Application mContext;

    @Inject
    public CacheHttpClient() {
    }

    @Override
    public OkHttpClient.Builder customBuild(OkHttpClient.Builder builder) {
        File cacheFile = new File(mContext.getCacheDir(), CACHE_DIR);
        builder.cache(new Cache(cacheFile, CACHE_SIZE));
        builder.addInterceptor(mCacheInterceptor);
        return builder;
    }

    private final Interceptor mCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetworkUtil.isNetworkAvailable(mContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);

            if (NetworkUtil.isNetworkAvailable(mContext)) {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                        .build();
            }
        }
    };
}
