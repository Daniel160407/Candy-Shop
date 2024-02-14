package com.mziuri.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddProductResponse {
    private String name;
    private Integer remainingAmount;
}
