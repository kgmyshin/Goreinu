package com.kgmyshin.goreinu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kgmyshin on 15/06/18.
 */
public class GoreinuReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent goreinuIntent = new Intent(context, GoreinuService.class);
        context.startService(goreinuIntent);
    }
}
