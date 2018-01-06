package me.uplan.stillcoder.gayhub.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import me.uplan.stillcoder.gayhub.di.annotation.ActivityContext;

/**
 * Created by mac on 2018/1/6.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity=activity;
    }

    public ActivityModule(Fragment fragment){
        mActivity=fragment.getActivity();
    }

    @Provides
    Activity provideActivity(){
        return mActivity;
    }

    @ActivityContext
    @Provides
    Context provideActivityContext(){
        return mActivity;
    }
}
