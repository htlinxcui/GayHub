package me.uplan.stillcoder.gayhub.data.http.api;

import io.reactivex.Observable;
import me.uplan.stillcoder.gayhub.data.model.User;

/**
 * Created by mac on 2018/1/6.
 */

public interface AccountApi {
    Observable<User> login(String username, String password);
}
