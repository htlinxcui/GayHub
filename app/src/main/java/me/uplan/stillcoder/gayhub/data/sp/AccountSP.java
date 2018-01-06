package me.uplan.stillcoder.gayhub.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import me.uplan.stillcoder.gayhub.data.model.User;
import me.uplan.stillcoder.library.util.GsonUtil;

/**
 * Created by mac on 2018/1/6.
 */

public class AccountSP {
    private static final String NAME_SP = "account_sp";
    private static final String KEY_LOGIN_TOKEN = "login_token";
    private static final String KEY_LOGON_USER = "logon_user";

    private static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(NAME_SP, Context.MODE_PRIVATE);
    }

    public static void saveLogonToken(Context context, String loginToken) {
        getPreference(context).edit().putString(KEY_LOGIN_TOKEN, loginToken).apply();
    }

    public static String getLogonToken(Context context) {
        return getPreference(context).getString(KEY_LOGIN_TOKEN, "");
    }

    public static void saveLogonUser(Context context, User user) {
        String userJson = GsonUtil.toJson(user);
        getPreference(context).edit().putString(KEY_LOGON_USER, userJson).apply();
    }

    public static void removeLoginUser(Context context) {
        getPreference(context).edit().remove(KEY_LOGON_USER).apply();
    }

    public static User getLogonUser(Context context) {
        User user = null;
        String userJson = getPreference(context).getString(KEY_LOGON_USER, "");
        if (!TextUtils.isEmpty(userJson)) {
            user = GsonUtil.fromJson(userJson, User.class);
        }
        return user;
    }

    public static boolean hasLogin(Context context) {
        return !TextUtils.isEmpty(getLogonToken(context)) && getLogonUser(context) != null;
    }

    public static boolean checkLogin(Context context) {
        if (!hasLogin(context)) {
            return false;
        }

        return true;
    }
}
