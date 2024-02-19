package com.mziuri.response;

public class GetProductResponse {
    private String name;
    private Integer remainingAmount;

    public GetProductResponse(String name, Integer remainingAmount) {
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
