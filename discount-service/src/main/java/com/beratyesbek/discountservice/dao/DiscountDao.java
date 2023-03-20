package com.beratyesbek.discountservice.dao;

import com.beratyesbek.discountservice.entities.DbDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DiscountDao extends JpaRepository<DbDiscount, Integer> {

    DbDiscount findDiscountByCode(@Param("code") String code);
}
