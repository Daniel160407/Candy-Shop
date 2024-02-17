package com.mziuri.servlet;

import com.mziuri.JDBC.MySQLController;
import com.mziuri.storage.StorageReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        StorageReader.getInstance().readAndAddProducts(mySQLController.getEntityManager());
        System.out.println(mySQLController.getProducts());

    }
}