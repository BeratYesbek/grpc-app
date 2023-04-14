package com.beratyesbek.discountservice.api;

import com.beratyesbek.discountservice.dao.DiscountDao;
import com.beratyesbek.discountservice.dto.DiscountApiRequest;
import com.beratyesbek.discountservice.dto.DiscountApiResponse;
import com.beratyesbek.discountservice.entities.DbDiscount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/discount")
public class DiscountsController {

    private final DiscountDao dao;

    public DiscountsController(DiscountDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public ResponseEntity<DbDiscount> create(@RequestBody DbDiscount discount) {
        return new ResponseEntity<DbDiscount>(
                dao.save(discount),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/getDiscount")
    public ResponseEntity<DiscountApiResponse> getDiscount(@RequestBody DiscountApiRequest discountRequest) {
        DbDiscount discount = dao.findDiscountByCode(discountRequest.getCode());
        DiscountApiResponse discountResponse;

        if (discount != null) {
            discountResponse = DiscountApiResponse.builder()
                    .message("Code is valid")
                    .statusCode(true)
                    .code(discountRequest.getCode())
                    .newPrice(discountRequest.getPrice() - discount.getDiscountPrice())
                    .oldPrice(discountRequest.getPrice())
                    .build();
        } else {
            discountResponse = DiscountApiResponse.builder()
                    .message("Code is invalid")
                    .statusCode(false)
                    .code(discountRequest.getCode())
                    .newPrice(discountRequest.getPrice() - discount.getDiscountPrice())
                    .oldPrice(discountRequest.getPrice())
                    .build();
        }
        return new ResponseEntity<>(
                discountResponse,
                HttpStatus.OK
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<DbDiscount> findByCode(@PathVariable String code) {
        return new ResponseEntity<>(dao.findDiscountByCode(code), HttpStatus.OK);
    }
}
