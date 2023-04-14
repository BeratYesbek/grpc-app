package com.beratyesbek.discountservice.grpc;

import com.beratyesbek.discountservice.dao.DiscountDao;
import com.beratyesbek.discountservice.entities.DbDiscount;
import com.beratyesbek.grpc.DiscountRequest;
import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.grpc.DiscountServiceGrpc;
import com.beratyesbek.grpc.Response;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {

    private DiscountDao dao;

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {
        DbDiscount discount = dao.findDiscountByCode(request.getCode());
        DiscountResponse discountResponse;

        if (discount != null) {
            discountResponse = DiscountResponse.newBuilder()
                    .setResponse(Response.newBuilder().setMessage("Code is valid").setStatusCode(true).build())
                    .setCode(request.getCode())
                    .setNewPrice(request.getPrice() - discount.getDiscountPrice())
                    .setOldPrice(request.getPrice())
                    .build();
        } else {
            discountResponse = DiscountResponse.newBuilder()
                    .setResponse(Response.newBuilder().setMessage("Code is invalid").setStatusCode(false).build())
                    .setCode(request.getCode())
                    .setNewPrice(request.getPrice())
                    .setOldPrice(request.getPrice())
                    .build();
        }

        responseObserver.onNext(discountResponse);
        responseObserver.onCompleted();
        super.getDiscount(request, responseObserver);
    }
}
