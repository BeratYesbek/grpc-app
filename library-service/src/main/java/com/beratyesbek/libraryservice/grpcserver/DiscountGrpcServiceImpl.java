package com.beratyesbek.libraryservice.grpcserver;

import com.beratyesbek.grpc.DiscountRequest;
import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.grpc.DiscountServiceGrpc;
import com.beratyesbek.libraryservice.entities.DbBook;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
 public class DiscountGrpcServiceImpl implements DiscountGrpcService {
    private DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceStub;
    private ManagedChannel channel;

    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost, @Value("${discount.grpc.port}") int grpcPort) {
        System.out.println("--> Discount Grpc information: " + grpcHost + ":" + grpcPort);
        channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Override
    public DiscountResponse getDiscount(DbBook dbBook, String code) {
        discountServiceStub = DiscountServiceGrpc.newBlockingStub(channel);
        DiscountResponse discountResponse = discountServiceStub.getDiscount(
                DiscountRequest.newBuilder()
                        .setCode(code)
                        .setBookName(dbBook.getName())
                        .setPrice(dbBook.getPrice().floatValue())
                        .setInternalId(dbBook.getId())
                        .build()
        );
        return discountResponse;
    }
}
