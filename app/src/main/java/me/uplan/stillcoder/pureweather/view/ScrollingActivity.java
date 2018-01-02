package me.uplan.stillcoder.pureweather.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import contract.IListReposContract;
import me.uplan.stillcoder.library.activity.BaseActivity;
import me.uplan.stillcoder.pureweather.R;
import model.http.bean.Repo;
import presenter.ListRepoPresenter;

public class ScrollingActivity extends BaseActivity implements IListReposContract.IRepoListView {

    IListReposContract.IListReposPresenter mPresenter;

    @BindView(R.id.tv_repos)
    TextView tvRepos;

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

        mPresenter = new ListRepoPresenter(this, this);
        mPresenter.listRepos("octocat");
    }

    @Override
    protected int setContainerId() {
        return R.layout.activity_scrolling;
    }

    @Override
    public void toastLoadingStartInfo() {
        Toast.makeText(this,"loading start",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshReposList(List<Repo> users) {
        tvRepos.setText(users.toString());
    }

    @Override
    public void toastLoadingFinishInfo() {
        Toast.makeText(this,"loading finish",Toast.LENGTH_SHORT).show();
    }
}
