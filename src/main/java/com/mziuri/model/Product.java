package com.mziuri.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")

public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prod_id;

    @Column(name = "prod_name", unique = true)
    private String prod_name;

    @Column(name = "prod_price")
    private float prod_price;

    @Column(name = "prod_amount")
    private int prod_amount;

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

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public void setProd_price(float prod_price) {
        this.prod_price = prod_price;
    }

    public void setProd_amount(int prod_amount) {
        this.prod_amount = prod_amount;
    }
}
