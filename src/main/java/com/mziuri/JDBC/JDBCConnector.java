package com.mziuri.JDBC;

import com.mziuri.model.Product;
import com.mziuri.model.Storage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class JDBCConnector {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("candyshop");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();

    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    private CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    private CriteriaQuery<Storage> storageCriteriaQuery = criteriaBuilder.createQuery(Storage.class);

    private Root<Product> productsRoot = criteriaQuery.from(Product.class);
    private Root<Storage> storageRoot = criteriaQuery.from(Storage.class);
    private static JDBCConnector instance;

    public static JDBCConnector getInstance() {
        if (instance == null) {
            instance = new JDBCConnector();
        }
        return instance;
    }

    public void initializeCriteria() {
        this.criteriaQuery = criteriaBuilder.createQuery(Product.class);
        this.productsRoot = criteriaQuery.from(Product.class);
    }

    public void initializeStorageCriteria() {
        this.storageCriteriaQuery = criteriaBuilder.createQuery(Storage.class);
        this.storageRoot = storageCriteriaQuery.from(Storage.class);
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

    public CriteriaQuery<Storage> getStorageCriteriaQuery() {
        return storageCriteriaQuery;
    }

    public Root<Storage> getStorageRoot() {
        return storageRoot;
    }
}