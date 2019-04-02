package com.example.john_lewis.ui.home.di;

import com.example.john_lewis.network.JohnLewisService;
import com.example.john_lewis.ui.home.mvp.HomeContract;
import com.example.john_lewis.ui.home.mvp.HomePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

     @Provides
     @HomeScope
     public HomeContract.HomePresenter provideHomePresenter(HomeContract.HomeView view, JohnLewisService service) {
         return new HomePresenter(view,service);
     }

}
