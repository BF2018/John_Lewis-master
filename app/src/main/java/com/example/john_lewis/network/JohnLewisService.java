package com.example.john_lewis.network;

import com.example.john_lewis.common.Constants;
import com.example.john_lewis.data.Products;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JohnLewisService {

    @GET(Constants.END_POINT)
    Observable<Products> getListOfProducts(@Query("key") String apiKey);
}
