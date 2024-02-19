package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;

import java.util.List;

public interface JDBCController {
    List<Product> getProducts();

    List<GetProductResponse> getProductResponse();

    GetProductInfoResponse getProductInfoResponse(int id);

    void addNewProduct(Product product);
}
