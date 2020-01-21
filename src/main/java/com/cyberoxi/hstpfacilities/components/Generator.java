package com.cyberoxi.hstpfacilities.components;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Component
public class Generator {

    private Random random;

    public Generator() {
        random = new Random(System.currentTimeMillis());
    }

    public String generateRandomNumber(int length) {
        String numbers = "1234567890";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++)
            stringBuilder.append(numbers.charAt(random.nextInt(numbers.length())));
        return stringBuilder.toString();
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
