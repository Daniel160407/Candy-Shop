package com.mziuri.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductInfoResponse {
    private String name;
    private Float price;
    private Integer amount;
}
