package com.mziuri.response;

public class AddProductResponse {
    private String name;
    private Integer remainingAmount;

    public AddProductResponse(String name, Integer remainingAmount) {
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
