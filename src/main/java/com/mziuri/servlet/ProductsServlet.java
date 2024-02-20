package com.mziuri.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.JDBC.JDBCConnector;
import com.mziuri.JDBC.MySQLController;
import com.mziuri.request.PurchaseRequest;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.PurchaseResponse;
import com.mziuri.storage.StorageReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/store/products")
public class ProductsServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        int prodId = Integer.parseInt(request.getParameter("id"));

        GetProductInfoResponse getProductInfoResponse = mySQLController.getProductInfoResponse(prodId);

        if (getProductInfoResponse != null) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(new ObjectMapper().writeValueAsString(getProductInfoResponse));
        } else {
            response.setStatus(405);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        PurchaseRequest purchaseRequest = new PurchaseRequest(request.getParameter("name"), Integer.parseInt(request.getParameter("amount")));

        PurchaseResponse purchaseResponse = mySQLController.getPurchaseResponse(purchaseRequest);

        if (purchaseResponse != null) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(new ObjectMapper().writeValueAsString(purchaseResponse));
        } else {
            response.setStatus(405);
        }
    }
}