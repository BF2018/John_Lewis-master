package com.example.john_lewis.app;

import android.app.Application;

import com.example.john_lewis.di.AppComponent;
import com.example.john_lewis.di.DaggerAppComponent;


public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
