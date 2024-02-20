package com.mziuri.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PurchaseResponse {
    private String name;
    private Integer remainingAmount;

    public PurchaseResponse(String name, Integer remainingAmount) {
        this.name = name;
        this.remainingAmount = remainingAmount;
    }

    public String getName() {
        return name;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }
}
