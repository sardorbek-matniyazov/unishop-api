package com.uniteam.unishop;

import com.uniteam.unishop.domain.enums.PaymentStatus;
import com.uniteam.unishop.repository.ClientRepo;
import com.uniteam.unishop.repository.OutputRepo;
import com.uniteam.unishop.repository.ProductRepo;
import com.uniteam.unishop.service.serviceImpl.PaymentHistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@SpringBootTest
class SmartShopApiApplicationTests {

    @Autowired
    OutputRepo outputRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PaymentHistoryServiceImpl paymentHistoryService;

    @Test
    void contextLoads() {
    }

    @Test
    void outputOpenInAndExit() {
        outputRepo.findAllByOpenInAndExit(
                Timestamp.valueOf(LocalDateTime.now().minusDays(50)),
                Timestamp.valueOf(LocalDateTime.now().plusDays(50))
        ).forEach(System.out::println);
    }

    @Test
    void checkSumsForLastSevenMonth(){
        paymentHistoryService.getSumLastSevenMonths().forEach(System.out::println);
    }

    @Test
    void findClientsWithName(){
        clientRepo.findAllByFullNameLike("ga").forEach(System.out::println);
    }

    @Test
    void findProductsWithName(){
        productRepo.findAllByNameLike("suct").forEach(System.out::println);
    }

    @Test
    void findByDebtClientName(){
        outputRepo.findAllByStatusAndClient_FullNameLike(PaymentStatus.DEBT, "ga").forEach(System.out::println);
    }
}
