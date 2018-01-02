package presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

import contract.IListReposContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.uplan.stillcoder.library.model.http.exception.ApiException;
import me.uplan.stillcoder.library.model.http.observer.BaseObserver;
import me.uplan.stillcoder.library.presenter.SimplePresenter;
import model.http.api.ListRepoApi;
import model.http.bean.Repo;

/**
 * @descript:获取repo列表执行类
 * @auther:stillcoder
 * @date:2018/1/2
 * @todoDescript:
 */

public class ListRepoPresenter extends SimplePresenter<IListReposContract.IRepoListView>
        implements IListReposContract.IListReposPresenter {

    private ListRepoApi api = new ListRepoApi();

    public ListRepoPresenter(@NonNull IListReposContract.IRepoListView view, @Nullable
            LifecycleProvider provider) {
        super(view, provider);
    }

    @Override
    public void listRepos(String user) {
        BaseObserver observer = new BaseObserver<List<Repo>>() {


            @Override
            protected void onStart(Disposable d) {
                if (viewRef.get() != null) {
                    viewRef.get().toastLoadingStartInfo();
                }
            }

            @Override
            protected void onSuccess(List<Repo> response) {
                if (viewRef.get() != null) {
                    response = response == null ? (new ArrayList<Repo>()) : response;
                    viewRef.get().refreshReposList(response);
                }
            }

            @Override
            protected void onError(ApiException e) {

            }
        };
        api.listRepos(user).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe
                (observer);
    }
}
