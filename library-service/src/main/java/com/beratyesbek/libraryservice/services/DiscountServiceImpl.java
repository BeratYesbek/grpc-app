package com.beratyesbek.libraryservice.services;

import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.grpc.Response;
import com.beratyesbek.libraryservice.dto.DiscountApiResponse;
import com.beratyesbek.libraryservice.entities.DbBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final String uri = "http://localhost:8081/api/discount/getDiscount";

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


        ResponseEntity response = restTemplate.exchange(uri, HttpMethod.POST, entity, DiscountApiResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return (DiscountApiResponse) response.getBody();
        } else {
            return null;
        }

    }
}
