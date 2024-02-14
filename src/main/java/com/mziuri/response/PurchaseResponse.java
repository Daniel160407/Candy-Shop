package com.mziuri.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseResponse {
    private String name;
    private Integer remainingAmount;
}
