package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.request.PurchaseRequest;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;
import com.mziuri.response.PurchaseResponse;

import java.util.List;

public interface JDBCController {
    List<Product> getProducts();

    List<GetProductResponse> getProductResponse();

    GetProductInfoResponse getProductInfoResponse(int id);

    PurchaseResponse getPurchaseResponse(PurchaseRequest purchaseRequest);

    void addNewProduct(Product product);
}
