package me.uplan.stillcoder.gayhub.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.uplan.stillcoder.gayhub.di.annotation.ApplicationContext;
import me.uplan.stillcoder.gayhub.di.module.ApplicationModule;

/**
 * Application依赖提供者和依赖需求者的中间人
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();


}
