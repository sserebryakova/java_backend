package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketApi {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Long id);

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Object> updateProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);
}