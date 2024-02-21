package com.mziuri.storage;

import com.mziuri.model.Product;

import java.util.List;

public class StorageConfig {
    private String password;
    private List<Product> products;

    public String getPassword() {
        return password;
    }

    public List<Product> getProducts() {
        return products;
    }
}