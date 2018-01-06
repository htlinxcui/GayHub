package me.uplan.stillcoder.library.util;



import com.google.gson.Gson;
import com.jakewharton.rxbinding2.internal.Preconditions;

/**
 * Created by mac on 2018/1/6.
 */

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        return gson.fromJson(str, clazz);
    }
}
