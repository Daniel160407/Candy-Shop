package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.model.Storage;
import com.mziuri.request.AddProductRequest;
import com.mziuri.request.PurchaseRequest;
import com.mziuri.response.AddProductResponse;
import com.mziuri.response.GetProductInfoResponse;
import com.mziuri.response.GetProductResponse;
import com.mziuri.response.PurchaseResponse;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class MySQLController implements JDBCController {
    private CriteriaQuery<Product> select;
    private TypedQuery<Product> productTypedQuery;
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();

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
        ).where(jdbcConnector.getCriteriaBuilder().equal(jdbcConnector.getProductsRoot().get("prod_name"), purchaseRequest.name()));

        productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);

        try {
            Product product = productTypedQuery.getSingleResult();
            if (product.getProd_amount() >= purchaseRequest.amount()) {
                jdbcConnector.getEntityTransaction().begin();

                Product productToBeUpdated = jdbcConnector.getEntityManager().find(Product.class, product.getProd_id());
                productToBeUpdated.setProd_amount(product.getProd_amount() - purchaseRequest.amount());

                jdbcConnector.getEntityTransaction().commit();

                return new PurchaseResponse(product.getProd_name(), (product.getProd_amount() - purchaseRequest.amount()));
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
    public AddProductResponse addProducts(AddProductRequest addProductRequest) {
        jdbcConnector.initializeStorageCriteria();

        CriteriaQuery<Storage> storageSelect = jdbcConnector.getStorageCriteriaQuery().multiselect(
                jdbcConnector.getStorageRoot().get("password")
        );

        TypedQuery<Storage> storageTypedQuery = jdbcConnector.getEntityManager().createQuery(storageSelect);
        Storage storage = storageTypedQuery.getSingleResult();

        if (storage.getPassword().equals(addProductRequest.password())) {
            try {
                jdbcConnector.initializeCriteria();

                select = jdbcConnector.getCriteriaQuery().multiselect(
                        jdbcConnector.getProductsRoot().get("id")
                ).where(jdbcConnector.getCriteriaBuilder().equal(jdbcConnector.getProductsRoot().get("prod_name"), addProductRequest.name()));

                productTypedQuery = jdbcConnector.getEntityManager().createQuery(select);
                Product product = productTypedQuery.getSingleResult();

                Product productToBeUpdated = jdbcConnector.getEntityManager().find(Product.class, product.getProd_id());

                if (productToBeUpdated != null) {
                    jdbcConnector.getEntityTransaction().begin();

                    productToBeUpdated.setProd_amount(addProductRequest.amount());

                    jdbcConnector.getEntityTransaction().commit();

                    return new AddProductResponse(addProductRequest.name(), addProductRequest.amount());
                } else {
                    return null;
                }
            } catch (Exception e) {
                if (jdbcConnector.getEntityTransaction().isActive()) {
                    jdbcConnector.getEntityTransaction().rollback();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }

        return null;
    }
}
