package com.example.john_lewis;

import com.example.john_lewis.data.ColorSwatch;
import com.example.john_lewis.data.Now;
import com.example.john_lewis.data.Price;
import com.example.john_lewis.data.Product;
import com.example.john_lewis.data.Products;
import com.example.john_lewis.network.JohnLewisService;
import com.example.john_lewis.ui.home.mvp.HomeContract;
import com.example.john_lewis.ui.home.mvp.HomePresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    private static final String KEY = "test";

    @Mock
    private JohnLewisService service;
    @Mock
    private HomeContract.HomeView homeView;
    @Mock
    private Products products;

    @Mock
    private Product product;

    @Mock
    private Price price;

    @Mock
    private Now now;

    @Mock
    private ColorSwatch colorSwatch;

    private HomePresenter homePresenter;

    private List<Product> productList;

    private InOrder order;

    @BeforeClass
    public static void setupRxSchedulers() {
        Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> scheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> scheduler);
    }


    @Before
    public void setUp() {
        homePresenter = new HomePresenter(homeView, service);
        products = new Products(productList);

        productList = Collections.singletonList(product);

        order = inOrder(homeView, service);
        when(service.getListOfProducts(KEY)).thenReturn(Observable.just(products));


    }

    @Test
    public void getApiData_WithTestKey_GetsResults() {
        homePresenter.getApiData();
        order.verifyNoMoreInteractions();
    }

    @Test
    public void filterData() {
    }

    @Test
    public void getImageUrl() {
    }

    @Test
    public void onError() {
    }

    @Test
    public void stop() {
    }
}