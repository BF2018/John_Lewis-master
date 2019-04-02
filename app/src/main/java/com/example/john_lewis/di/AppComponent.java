package com.example.john_lewis.di;


import android.app.Application;

import com.example.john_lewis.network.JohnLewisService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,NetworkModule.class})
public interface AppComponent {

  JohnLewisService johnLewisService();

    @Component.Builder
    interface Builder {
        AppComponent build();
        @BindsInstance
        Builder application(Application application);
    }
}
