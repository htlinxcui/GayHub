package me.uplan.stillcoder.gayhub.data.http.service;


import io.reactivex.Observable;
import me.uplan.stillcoder.gayhub.data.http.request.AuthorizationParam;
import me.uplan.stillcoder.gayhub.data.http.response.AuthorizationResponse;
import me.uplan.stillcoder.gayhub.data.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by mac on 2018/1/6.
 */

public interface AccountService {

    @POST("/authorizations")
    Observable<AuthorizationResponse> createAuthorization(@Body AuthorizationParam param);

    @GET("/user")
    Observable<User> getUserInfo(@Query("access_token") String accessToken);
}
