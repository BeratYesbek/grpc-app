package com.beratyesbek.libraryservice.services;

import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.grpc.Response;
import com.beratyesbek.libraryservice.dto.DiscountApiResponse;
import com.beratyesbek.libraryservice.entities.DbBook;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountServiceImpl implements DiscountService {
    private StringBuilder uri;

    public DiscountServiceImpl(@Value("${discount.api.host}") String host, @Value("${discount.api.port}") int port) {
        uri = new StringBuilder("http://" + host + ":" + port + "/api/discount/getDiscount");

    }

    @Override
    public DiscountApiResponse getDiscount(DbBook dbBook, String code) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");


        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("price", dbBook.getPrice());
        map.put("internalId", dbBook.getId());
        map.put("bookName", dbBook.getName());


        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);


        ResponseEntity response = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, DiscountApiResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return (DiscountApiResponse) response.getBody();
        } else {
            return null;
        }

    }
}
