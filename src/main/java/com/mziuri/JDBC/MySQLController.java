package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
        jdbcConnector.initializeCriteria();

        select = jdbcConnector.getCriteriaQuery().multiselect(
                jdbcConnector.getProductsRoot().get("prod_name"),
                jdbcConnector.getProductsRoot().get("prod_amount")
        );
        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        List<Product> products = productTypedQuery.getResultList();

        List<GetProductResponse> getProductResponseList = new ArrayList<>();

        products.forEach(product -> getProductResponseList.add(new GetProductResponse(product.getProd_name(), product.getProd_amount())));

        return getProductResponseList;
    }

    @Override
    public GetProductInfoResponse getProductInfoResponse(int id) {
        jdbcConnector.initializeCriteria();

        select = jdbcConnector.getCriteriaQuery().multiselect(
                jdbcConnector.getProductsRoot().get("prod_name"),
                jdbcConnector.getProductsRoot().get("prod_price"),
                jdbcConnector.getProductsRoot().get("prod_amount")
        ).where(jdbcConnector.getCriteriaBuilder().equal(jdbcConnector.getProductsRoot().get("prod_id"), id));

        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        Product product = productTypedQuery.getSingleResult();

        return new GetProductInfoResponse(product.getProd_name(), product.getProd_price(), product.getProd_amount());
    }


    @Override
    public void addNewProduct(Product product) {

    }
}
