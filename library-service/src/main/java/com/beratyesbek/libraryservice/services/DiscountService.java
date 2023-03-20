package com.beratyesbek.libraryservice.services;

import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.libraryservice.dto.DiscountApiResponse;
import com.beratyesbek.libraryservice.entities.DbBook;

public interface DiscountService {
     DiscountApiResponse getDiscount(DbBook dbBook, String code);
}
