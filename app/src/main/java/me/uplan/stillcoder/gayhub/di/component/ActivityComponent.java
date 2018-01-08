package me.uplan.stillcoder.gayhub.di.component;

import android.app.Activity;

import dagger.Component;
import me.uplan.stillcoder.gayhub.di.annotation.PerActivity;
import me.uplan.stillcoder.gayhub.di.module.ActivityModule;

/**
 * Activity依赖提供和依赖需求的中间人，本身依赖于ApplicationComponent，以获取Application依赖
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
