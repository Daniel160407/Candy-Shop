package com.mziuri.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.JDBC.JDBCConnector;
import com.mziuri.JDBC.MySQLController;
import com.mziuri.storage.StorageReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/store")
public class StoreServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();

    @Override
    public void init() {
        StorageReader.getInstance().readAndAddProducts(JDBCConnector.getInstance().getEntityManager());
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        PrintWriter printWriter = response.getWriter();
        System.err.println(new ObjectMapper().writeValueAsString(mySQLController.getProductResponse()));
        printWriter.println(new ObjectMapper().writeValueAsString(mySQLController.getProductResponse()));
    }
}
