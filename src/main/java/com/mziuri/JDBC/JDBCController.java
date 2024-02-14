package com.mziuri.JDBC;

import com.mziuri.model.Product;

import java.util.List;

public interface JDBCController {
    List<Product> getProducts();

    Product getProductById(int id);

    void addNewProduct(Product product);
}
