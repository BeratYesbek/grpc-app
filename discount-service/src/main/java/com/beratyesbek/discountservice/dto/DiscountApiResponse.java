package com.beratyesbek.discountservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DiscountApiResponse {
    private String code;
    private int newPrice;
    private int oldPrice;
    private String message;
    private boolean statusCode;

}
