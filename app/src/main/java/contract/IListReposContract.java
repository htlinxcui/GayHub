package contract;

import java.util.List;

import me.uplan.stillcoder.library.view.IView;
import model.http.bean.Repo;

/**
 * @descript:
 * @auther:stillcoder
 * @date:2018/1/2
 * @todoDescript:
 */

public interface IListReposContract {
    public interface IRepoListView extends IView {
        void toastLoadingStartInfo();
        void refreshReposList(List<Repo> users);
        void toastLoadingFinishInfo();
    }

    public interface IListReposPresenter{
        void listRepos(String user);
    }
}
