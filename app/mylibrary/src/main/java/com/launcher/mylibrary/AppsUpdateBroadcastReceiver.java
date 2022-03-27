package com.launcher.mylibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*Receiver to logs update for install and uninstall apps*/
public class AppsUpdateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData().getEncodedSchemeSpecificPart();
        switch (intent.getAction()) {
            case Intent.ACTION_PACKAGE_REMOVED:

                break;
            case Intent.ACTION_PACKAGE_ADDED:
                Log.e("AppsUpdateBroadcastReceiver", "App Added: ${packageName ?: }");

                break;

        }
    }
}
