package com.kgmyshin.goreinu;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
import static android.content.pm.PackageManager.DONT_KILL_APP;

/**
 * Created by kgmyshin on 15/06/17.
 */
public class Goreinu {
    private static Executor enableExecutor = Executors.newSingleThreadExecutor();

    public static void install(Context context) {
        setEnabled(context, GoreinuActivity.class, true);
        setEnabled(context, GoreinuService.class, true);
        setEnabled(context, GoreinuReceiver.class, true);
    }

    private static void setEnabled(Context context, final Class<?> componentClass,
                                  final boolean enabled) {
        final Context appContext = context.getApplicationContext();
        enableExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ComponentName component = new ComponentName(appContext, componentClass);
                PackageManager packageManager = appContext.getPackageManager();
                int newState = enabled ? COMPONENT_ENABLED_STATE_ENABLED : COMPONENT_ENABLED_STATE_DISABLED;
                packageManager.setComponentEnabledSetting(component, newState, DONT_KILL_APP);
            }
        });
    }

}
