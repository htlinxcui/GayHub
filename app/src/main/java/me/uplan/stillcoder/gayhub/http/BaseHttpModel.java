package me.uplan.stillcoder.gayhub.http;

import me.uplan.stillcoder.gayhub.common.constants.ApiConstants;
import me.uplan.stillcoder.library.data.http.ApiSingleton;
import me.uplan.stillcoder.library.data.http.configuration.ApiConfiguration;

/**
 * @descript:
 * @auther:stillcoder
 * @date:2018/1/2
 * @todoDescript:
 */

public abstract class BaseHttpModel {
    protected final <T> T createSimpleRequest(Class<T> cls) {
        ApiConfiguration configuration = ApiConfiguration.builder().baseUrl(ApiConstants
                .BASE_URL).build();
        ApiSingleton.getInstance().init(configuration);
        return ApiSingleton.getInstance().createService(cls);
    }
}
