package com.beratyesbek.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountApiResponse {
    private String code;
    private float newPrice;
    private float oldPrice;
    private String message;
    private boolean statusCode;

}
