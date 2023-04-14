package com.beratyesbek.discountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountApiRequest {
    @JsonProperty("code")
    private String code;
    @JsonProperty("price")
    private int price;
    @JsonProperty("internalId")
    private int internalId;
    @JsonProperty("bookName")
    private String bookName;
}
