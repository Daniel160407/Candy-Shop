package com.mziuri.JDBC;

import com.mziuri.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class JDBCConnector {
    protected final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("candyshop");
    protected final EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected final EntityTransaction entityTransaction = entityManager.getTransaction();

    protected final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    protected final CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

    protected final Root<Product> productsRoot = criteriaQuery.from(Product.class);

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery<Product> getCriteriaQuery() {
        return criteriaQuery;
    }

    public Root<Product> getProductsRoot() {
        return productsRoot;
    }
}