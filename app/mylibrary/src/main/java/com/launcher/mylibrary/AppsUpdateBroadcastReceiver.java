package com.launcher.mylibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/*Receiver to logs update for install and uninstall apps*/
public class AppsUpdateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getData().getEncodedSchemeSpecificPart();
        switch (intent.getAction()) {
            case Intent.ACTION_PACKAGE_REMOVED:
                Toast.makeText(context,"Uninstall App",Toast.LENGTH_LONG).show();
                break;
            case Intent.ACTION_PACKAGE_ADDED:
                Toast.makeText(context,"Installed App",Toast.LENGTH_LONG).show();

                break;

        }
    }
}
