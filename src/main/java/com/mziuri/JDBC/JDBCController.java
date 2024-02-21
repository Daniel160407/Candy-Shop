package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.request.AddProductRequest;
import com.mziuri.request.PurchaseRequest;
import com.mziuri.response.AddProductResponse;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;
import com.mziuri.response.PurchaseResponse;

import java.util.List;

public interface JDBCController {

    List<GetProductResponse> getProductResponse();

    GetProductInfoResponse getProductInfoResponse(int id);

    PurchaseResponse getPurchaseResponse(PurchaseRequest purchaseRequest);

    AddProductResponse addProducts(AddProductRequest addProductRequest);
}
