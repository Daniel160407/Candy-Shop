package com.mziuri.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddProductRequest {
    private String password;
    private String name;
    private Integer amount;
}
