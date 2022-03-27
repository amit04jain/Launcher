package com.launcher.mylibrary;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.UserHandle;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*AppsManager is singleton class to return the list of apps*/
public class AppsManager {
    public static final Comparator<AppInfo> ALPHA_COMPARATOR = new Comparator<AppInfo>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(AppInfo object1, AppInfo object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };
    private static AppsManager instance;

    public static AppsManager getInstance() {
        if (instance == null) {
            synchronized (AppsManager.class) {
                if (instance == null) {
                    instance = new AppsManager();//instance will be created at request time
                }
            }
        }
        return instance;
    }

    public List getInstalledApps(Context context) {
        List appsList = new ArrayList<AppInfo>();

        android.content.pm.LauncherApps launcherApps = (android.content.pm.LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
        List<UserHandle> profiles = launcherApps.getProfiles();
        for (UserHandle userHandle : profiles) {
            List<LauncherActivityInfo> apps = launcherApps.getActivityList(null, userHandle);
            for (LauncherActivityInfo info : apps) {
                AppInfo app = new AppInfo();
                app.label = info.getLabel().toString();
                app.name = info.getComponentName().getPackageName().toString();
                app.icon = info.getIcon(0);
                try {
                    app.versionName = context.getPackageManager().getPackageInfo(info.getComponentName().getPackageName(), 0).versionName;
                    app.versionCode = context.getPackageManager().getPackageInfo(info.getComponentName().getPackageName(), 0).versionCode;
                } catch (Exception e) {

                }
                appsList.add(app);
            }
        }
        Collections.sort(appsList, ALPHA_COMPARATOR);
        return appsList;
    }
}
