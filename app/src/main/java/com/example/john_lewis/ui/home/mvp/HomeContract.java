package com.example.john_lewis.ui.home.mvp;


import com.example.john_lewis.data.Products;


public interface HomeContract {
    interface HomeView {
        void displayData(Products product);
        void showResults(String message);
    }

    interface HomePresenter {
        void getApiData();

        void onError(Throwable message);

        void stop();

    }
}
