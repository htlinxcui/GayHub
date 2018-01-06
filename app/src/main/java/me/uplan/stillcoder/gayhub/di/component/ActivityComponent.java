package me.uplan.stillcoder.gayhub.di.component;

import android.app.Activity;

import dagger.Component;
import me.uplan.stillcoder.gayhub.di.annotation.PerActivity;
import me.uplan.stillcoder.gayhub.di.module.ActivityModule;

/**
 * Created by mac on 2018/1/6.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
