package me.uplan.stillcoder.library.ui.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by mac on 2018/1/7.
 */

public class RxClickableView {
    @CheckResult
    @NonNull
    public static Observable<Object> clicks(@NonNull View view) {
        return RxView.clicks(view)
                .throttleFirst(500, TimeUnit.MILLISECONDS);
    }
}
