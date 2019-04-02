package com.example.john_lewis.ui.home.di;


import com.example.john_lewis.di.AppComponent;
import com.example.john_lewis.ui.home.mvp.HomeActivity;
import com.example.john_lewis.ui.home.mvp.HomeContract;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = HomeModule.class,dependencies = AppComponent.class)
@HomeScope
public interface HomeComponent {

    void inject (HomeActivity homeActivity);

    @Component.Builder
    interface Builder {
        HomeComponent build();

        Builder appComponent(AppComponent appComponent);

        @BindsInstance
        Builder homeActivity(HomeContract.HomeView view);
    }
}
