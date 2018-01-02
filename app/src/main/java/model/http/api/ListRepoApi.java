package model.http.api;

import java.util.List;

import io.reactivex.Observable;
import model.http.BaseHttpModel;
import model.http.bean.Repo;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @descript:获取repo列表接口类
 * @auther:stillcoder
 * @date:2018/1/2
 * @todoDescript:
 */

public final class ListRepoApi extends BaseHttpModel {
    interface InnerApi {
        @GET("users/{user}/repos")
        Observable<List<Repo>> listRepos(@Path("user") String user);
    }

    InnerApi api = createSimpleRequest(InnerApi.class);

    public Observable<List<Repo>> listRepos(String user) {
        return api.listRepos(user);
    }
}
