package me.uplan.stillcoder.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @descript:网络工具类
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected() && networkinfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
