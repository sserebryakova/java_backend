package com.geekbrains.backend.test.miniMarket.model;

import java.util.List;

public class ProductsResponse {

    private List<Product> products;
    private String error;

    public ProductsResponse() {
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ProductsResponse{" +
                "products=" + products +
                ", error='" + error + '\'' +
                '}';
    }
}
