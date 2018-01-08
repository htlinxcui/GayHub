package me.uplan.stillcoder.gayhub;


import android.app.Application;
import android.content.Context;

import me.uplan.stillcoder.gayhub.di.component.ApplicationComponent;
import me.uplan.stillcoder.gayhub.di.component.DaggerApplicationComponent;
import me.uplan.stillcoder.gayhub.di.module.ApplicationModule;


/**
 * Created by mac on 2018/1/6.
 */

public class GayHubApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GayHubInitializeService.start(this);
    }

    public static GayHubApplication get(Context context) {
        return (GayHubApplication) context.getApplicationContext();
    }

    ApplicationComponent mApplicationComponent;

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
