package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.response.GetProductResponse;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class MySQLController implements JDBCController {
    private CriteriaQuery<Product> select;
    private TypedQuery<Product> productTypedQuery;
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();

    @Override
    public List<Product> getProducts() {
        select = jdbcConnector.getCriteriaQuery().select(jdbcConnector.getProductsRoot());
        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        return productTypedQuery.getResultList();
    }

    @Override
    public List<GetProductResponse> getProductResponse() {
        select = jdbcConnector.getCriteriaQuery().select(jdbcConnector.getProductsRoot().get("prod_name").get("prod_amount"));
        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        List<Product> products = productTypedQuery.getResultList();
        List<GetProductResponse> getProductResponseList = new ArrayList<>();

        products.forEach(product -> getProductResponseList.add(new GetProductResponse(product.getProd_name(), product.getProd_amount())));

        return getProductResponseList;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public void addNewProduct(Product product) {

    }
}
