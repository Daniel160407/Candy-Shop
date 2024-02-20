package com.mziuri.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PurchaseRequest {
    private String name;
    private Integer amount;

    public PurchaseRequest(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
