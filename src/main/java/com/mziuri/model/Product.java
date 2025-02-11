package com.mziuri.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "prod_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prod_id;

    @Column(name = "prod_name", unique = true)
    private String prod_name;

    @Column(name = "prod_price")
    private float prod_price;

    @Column(name = "prod_amount")
    private int prod_amount;

    public Product() {

    }

    public Product(String prod_name, int prod_amount) {
        this.prod_name = prod_name;
        this.prod_amount = prod_amount;
    }

    public Product(String prod_name, float prod_price, int prod_amount) {
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_amount = prod_amount;
    }

    public Product(int prod_id, String prod_name, int prod_amount) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.prod_amount = prod_amount;
    }

    public Product(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public float getProd_price() {
        return prod_price;
    }

    public int getProd_amount() {
        return prod_amount;
    }

    public void setProd_amount(int prod_amount) {
        this.prod_amount = prod_amount;
    }
}
