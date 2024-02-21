package com.mziuri.storage;

import com.mziuri.model.Product;

import java.util.List;

public class StorageConfig {
    private String password;
    private List<Product> products;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "StorageConfig{" +
                "password='" + password + '\'' +
                ", products=" + products +
                '}';
    }
}