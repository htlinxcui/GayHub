package me.uplan.stillcoder.gayhub.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.uplan.stillcoder.gayhub.R;
import me.uplan.stillcoder.library.presenter.IPresenterLifeCycle;
import me.uplan.stillcoder.library.ui.base.BaseActivity;

public class ScrollingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected int setContainerId() {
        return R.layout.activity_scrolling;
    }

    @Override
    protected IPresenterLifeCycle[] gatherPresenters() {
        return new IPresenterLifeCycle[0];
    }
}
