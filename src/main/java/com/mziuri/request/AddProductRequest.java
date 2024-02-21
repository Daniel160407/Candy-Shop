package com.mziuri.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AddProductRequest {
    private String password;
    private String name;
    private Integer amount;

    public AddProductRequest(String password, String name, Integer amount) {
        this.password = password;
        this.name = name;
        this.amount = amount;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
