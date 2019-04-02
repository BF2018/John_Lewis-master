package com.example.john_lewis.ui.home.mvp;

import com.example.john_lewis.common.Constants;
import com.example.john_lewis.data.Product;
import com.example.john_lewis.data.Products;
import com.example.john_lewis.network.JohnLewisService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.HomePresenter {

    private final HomeContract.HomeView view;
    private JohnLewisService service;
    private CompositeDisposable disposable;
    private Products discountedProducts;

    @Inject
    public HomePresenter(HomeContract.HomeView view, JohnLewisService service) {
        this.view = view;
        this.service = service;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getApiData() {
        view.showResults("Please wait loading data...");
        disposable.add(service.getListOfProducts(Constants.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> HomePresenter.this.filterData(products), this::onError));
    }


    public void filterData(@Nullable Products products) {

        List<Product> productList = new ArrayList<>();

        if (products != null) {
            for (Product product : products.getProducts()) {
                if (!(product.getPrice().getWas()).equals("")) {

                    product.getPrice().setDiscounted(product.getPrice().getNow());

                    productList.add(product);

                    Collections.sort(productList, (o1, o2) -> {

                        Double discountOne = Double.valueOf(o1.getPrice().getDiscounted());
                        Double discountTwo = Double.valueOf(o2.getPrice().getDiscounted());

                        if (discountOne.compareTo(discountTwo) < 0) {
                            return 1;
                        } else if (discountOne.compareTo(discountTwo) > 0) {
                            return -1;
                        } else {
                            return 0;
                        }
                    });

                    discountedProducts = new Products(productList);

                    view.displayData(discountedProducts);
                }


            }
        }

    }


    @Override
    public void onError(Throwable message) {
        view.showResults(message.getMessage());
    }



    @Override
    public void stop() {
        disposable.clear();
    }


}
