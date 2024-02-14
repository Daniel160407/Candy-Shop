package com.mziuri.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductsResponse {
    private String[] productNames;
}
