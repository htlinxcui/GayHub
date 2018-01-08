package me.uplan.stillcoder.gayhub;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by mac on 2018/1/6.
 */

public class GayHubInitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "me.uplan.stillcoder.gayhub.action" +
            ".INIT";

    public GayHubInitializeService() {
        super("GayHubInitializeService");
    }

    public static void start(Context context) {
        Intent initIntent = new Intent(context, GayHubInitializeService.class);
        initIntent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(initIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                init();
            }
        }
    }

    private void init() {

    }
}
