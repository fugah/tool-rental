package com.example.toolRental.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Helper class to handle BigDecimal calculations and rounding
 */
public class ChargeCalculator {

    private static final int DEFAULT_ROUNDING_SCALE = 2;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal calculatePreDiscountCharge(int chargeableDays, String dailyCharge) {
        return new BigDecimal(chargeableDays).multiply(new BigDecimal(dailyCharge)).setScale(DEFAULT_ROUNDING_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal calculateDiscountAmount(int discountPercent, BigDecimal preDiscountCharge) {
        BigDecimal discountAsDecimalMultiplier = new BigDecimal(discountPercent).multiply(new BigDecimal("0.01"));
        return preDiscountCharge.multiply(discountAsDecimalMultiplier).setScale(DEFAULT_ROUNDING_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal calculateFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        return preDiscountCharge.subtract(discountAmount);
    }
}
