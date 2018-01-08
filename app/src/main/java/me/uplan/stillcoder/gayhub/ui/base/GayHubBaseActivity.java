package me.uplan.stillcoder.gayhub.ui.base;

import me.uplan.stillcoder.gayhub.GayHubApplication;
import me.uplan.stillcoder.gayhub.di.component.ActivityComponent;
import me.uplan.stillcoder.gayhub.di.component.DaggerActivityComponent;
import me.uplan.stillcoder.gayhub.di.module.ActivityModule;
import me.uplan.stillcoder.library.ui.base.BaseActivity;

/**
 * Created by mac on 2018/1/7.
 */

public abstract class GayHubBaseActivity extends BaseActivity {

    private ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(GayHubApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
