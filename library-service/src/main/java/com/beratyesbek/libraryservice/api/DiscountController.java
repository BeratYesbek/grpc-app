package com.beratyesbek.libraryservice.api;

import com.beratyesbek.grpc.DiscountResponse;
import com.beratyesbek.libraryservice.dao.BookDao;
import com.beratyesbek.libraryservice.dto.DiscountApiResponse;
import com.beratyesbek.libraryservice.entities.DbBook;
import com.beratyesbek.libraryservice.grpcserver.DiscountGrpcService;
import com.beratyesbek.libraryservice.services.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("api/discount")
public class DiscountController {

    private static long totalTimeForGrpc = 0;
    private static long totalTimeForAPI = 0;
    private final DiscountGrpcService discountGrpcService;
    private final DiscountService discountService;
    private final BookDao bookDao;
    Logger logger = LoggerFactory.getLogger(DiscountController.class);

    public DiscountController(DiscountGrpcService discountGrpcService, DiscountService discountService, BookDao bookDao) {
        this.discountGrpcService = discountGrpcService;
        this.discountService = discountService;
        this.bookDao = bookDao;
    }
    // 5599ms grpc
    // 7967ms api
    @GetMapping("/withGrpc/{bookId}")
    public ResponseEntity getDiscountWithGrpc(@PathVariable int bookId, @RequestParam("code") String code) {
        long startedTime = System.nanoTime();
        DbBook dbBook = bookDao.findById(bookId).get();
        DiscountResponse discountResponse = discountGrpcService.getDiscount(dbBook, code);
        long endedTime = System.nanoTime();
        long time = ((endedTime - startedTime) / 1000000);
        logger.info("Grpc --> Grpc request took : " + time + "ms");
        totalTimeForGrpc = totalTimeForGrpc + time;
        logger.info("Total time for Grpc: " + totalTimeForGrpc + "ms");

        return new ResponseEntity(
                DiscountApiResponse.builder()
                        .statusCode(discountResponse.getResponse().getStatusCode())
                        .message(discountResponse.getResponse().getMessage())
                        .newPrice(discountResponse.getNewPrice())
                        .newPrice(discountResponse.getOldPrice())
                        .code(discountResponse.getCode())
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/withAPI/{bookId}")
    public ResponseEntity getDiscountWithAPI(@PathVariable int bookId, @RequestParam("code") String code) {
        long startedTime = System.nanoTime();
        DbBook dbBook = bookDao.findById(bookId).get();
        DiscountApiResponse discountResponse = discountService.getDiscount(dbBook, code);
        long endedTime = System.nanoTime();
        long time = ((endedTime - startedTime) / 1000000);
        logger.info("API --> Api request took : " + time + "ms");
        totalTimeForAPI = totalTimeForAPI + time;
        logger.info("Total time for API: " + totalTimeForAPI + "ms");

        return new ResponseEntity(
                discountResponse,
                HttpStatus.OK
        );
    }
}
