package com.mziuri.JDBC;

import com.mziuri.model.Product;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class MySQLController extends JDBCConnector implements JDBCController {
    private CriteriaQuery<Product> select;
    private TypedQuery<Product> productTypedQuery;

    @Override
    public List<Product> getProducts() {
        select = criteriaQuery.select(productsRoot);
        productTypedQuery = entityManager.createQuery(select);

        return productTypedQuery.getResultList();
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public void addNewProduct(Product product) {

    }
}
