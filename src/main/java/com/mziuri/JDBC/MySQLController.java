package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.request.PurchaseRequest;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;
import com.mziuri.response.PurchaseResponse;
import jakarta.persistence.NoResultException;
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

        try {
            Product product = productTypedQuery.getSingleResult();
            return new GetProductInfoResponse(product.getProd_name(), product.getProd_price(), product.getProd_amount());
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PurchaseResponse getPurchaseResponse(PurchaseRequest purchaseRequest) {
        jdbcConnector.initializeCriteria();

        select = jdbcConnector.getCriteriaQuery().multiselect(
                jdbcConnector.getProductsRoot().get("prod_id"),
                jdbcConnector.getProductsRoot().get("prod_name"),
                jdbcConnector.getProductsRoot().get("prod_amount")
        ).where(jdbcConnector.getCriteriaBuilder().equal(jdbcConnector.getProductsRoot().get("prod_name"), purchaseRequest.getName()));

        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        try {
            Product product = productTypedQuery.getSingleResult();
            if (product.getProd_amount() >= purchaseRequest.getAmount()) {
                jdbcConnector.getEntityTransaction().begin();

                Product productToBeUpdated = jdbcConnector.getEntityManager().find(Product.class, product.getProd_id());
                productToBeUpdated.setProd_amount(product.getProd_amount() - purchaseRequest.getAmount());

                jdbcConnector.getEntityTransaction().commit();

                return new PurchaseResponse(product.getProd_name(), (product.getProd_amount() - purchaseRequest.getAmount()));
            } else {
                return null;
            }
        } catch (NoResultException e) {
            if (jdbcConnector.getEntityTransaction().isActive()) {
                jdbcConnector.getEntityTransaction().rollback();
            }

            return null;
        }
    }


    @Override
    public void addNewProduct(Product product) {

    }
}
