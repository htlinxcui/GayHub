package me.uplan.stillcoder.gayhub.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.uplan.stillcoder.gayhub.di.annotation.ApplicationContext;

/**
 * 提供Application依赖
 */

@Module
public class ApplicationModule {
    protected Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @ApplicationContext
    @Provides
    Context provideApplicationContext() {
        return mApplication;
    }
}
