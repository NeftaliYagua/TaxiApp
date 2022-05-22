package com.example.taxiapp;

import android.app.Application;


public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
