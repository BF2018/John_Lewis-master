package com.example.john_lewis.ui.home.mvp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.john_lewis.R;
import com.example.john_lewis.app.App;
import com.example.john_lewis.common.Constants;
import com.example.john_lewis.data.Products;
import com.example.john_lewis.ui.home.adapter.HomeAdapter;
import com.example.john_lewis.ui.home.di.DaggerHomeComponent;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView {


    @Inject
    HomePresenter homePresenter;

    private HomeAdapter adapter;
    private RecyclerView recyclerView;
    LinearLayoutManager homeLayoutManager;
    ImageView topImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DaggerHomeComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .homeActivity(this)
                .build()
                .inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        homePresenter.getApiData();
        initCollapsingToolbar();

        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    public void displayData(Products product) {

        homeLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(homeLayoutManager);

        adapter = new HomeAdapter(product, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showResults(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


}
