package com.beratyesbek.discountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbDiscount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "discount_price")
    private int discountPrice;
}
