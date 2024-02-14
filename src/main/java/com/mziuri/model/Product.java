package com.mziuri.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prod_id;

    @Column(unique = true)
    private String prod_name;

    @Column
    private float prod_price;

    @Column
    private int prod_amount;

    @Override
    public String toString() {
        return "Product{" +
                "prod_id=" + prod_id +
                ", prod_name='" + prod_name + '\'' +
                ", prod_price=" + prod_price +
                ", prod_amount=" + prod_amount +
                '}';
    }
}
