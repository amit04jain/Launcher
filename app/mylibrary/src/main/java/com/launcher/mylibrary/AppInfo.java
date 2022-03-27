package com.launcher.mylibrary;


import android.graphics.drawable.Drawable;

/*Class to contain metadata of the app*/
public class AppInfo {

    String label;
    /*package name*/
    String name;

    Drawable icon;
    long versionCode;
    String versionName;
    String mainActivityClassName;


    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMainActivityClassName() {
        return mainActivityClassName;
    }

    public void setMainActivityClassName(String mainActivityClassName) {
        this.mainActivityClassName = mainActivityClassName;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

}