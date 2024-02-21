package com.mziuri.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.JDBC.JDBCConnector;
import com.mziuri.JDBC.MySQLController;
import com.mziuri.model.Product;
import com.mziuri.model.Storage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.io.File;

@RequiredArgsConstructor
public class StorageReader {
    private static final String STORAGE_FILE_PATH = "src/main/resources/storage.json";
    private final MySQLController mySQLController = new MySQLController();

    private static StorageReader instance;

    public static synchronized StorageReader getInstance() {
        if (instance == null) {
            instance = new StorageReader();
        }
        return instance;
    }

    public void readAndAddProducts(EntityManager entityManager) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(STORAGE_FILE_PATH);

            StorageConfig candyShopData = objectMapper.readValue(file, StorageConfig.class);

            entityManager.getTransaction().begin();

            Storage storage = new Storage(candyShopData.getPassword());
            entityManager.merge(storage);

            for (Product product : candyShopData.getProducts()) {
                entityManager.merge(product);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
