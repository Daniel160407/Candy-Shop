package com.mziuri.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseRequest {
    private String name;
    private Integer amount;
}
