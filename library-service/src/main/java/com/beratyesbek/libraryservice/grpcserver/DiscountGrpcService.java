package com.beratyesbek.libraryservice.grpcserver;

import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.libraryservice.entities.DbBook;

public interface DiscountGrpcService {
    DiscountResponse getDiscount(DbBook dbBook, String code);
}
