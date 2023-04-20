package com.syracuse.PowerSaverHQ.utils;

import java.util.Random;
import java.util.UUID;

public class generateAccountNumber {

    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private static final Random RANDOM = new Random();

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
