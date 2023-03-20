package com.beratyesbek.discountservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DiscountApiRequest {
/*
        map.put("code", code);
        map.put("price", dbBook.getPrice());
        map.put("internalId", dbBook.getId());
        map.put("bookName", dbBook.getName());
 */
    @JsonProperty("code")
    private String code;
    @JsonProperty("price")
    private int price;
    @JsonProperty("internalId")
    private int internalId;
    @JsonProperty("bookName")
    private String bookName;
}
