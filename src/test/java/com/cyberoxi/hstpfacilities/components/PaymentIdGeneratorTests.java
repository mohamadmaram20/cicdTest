package com.cyberoxi.hstpfacilities.components;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class PaymentIdGeneratorTests {

    private PaymentIdGenerator paymentIdGenerator;

    @Autowired
    PaymentIdGeneratorTests(PaymentIdGenerator paymentIdGenerator) {
        this.paymentIdGenerator = paymentIdGenerator;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void paymentIdTest() {
        assertEquals(paymentIdGenerator.forNaturalPerson("3860743376","20","72"), "27210476511355100203860743376");
    }

}
