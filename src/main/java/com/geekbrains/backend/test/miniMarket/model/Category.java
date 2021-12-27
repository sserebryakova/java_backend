package com.geekbrains.backend.test.miniMarket.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Long id;
    private List<Product> products = new ArrayList<>();
    private String title;


    public Category() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", products=" + products +
                ", title='" + title + '\'' +
                '}';
    }
}
