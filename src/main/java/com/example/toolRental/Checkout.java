package com.example.toolRental;

import com.example.toolRental.enums.Tool;
import com.example.toolRental.util.ChargeCalculator;
import com.example.toolRental.util.ChargeDaysHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
Generates RentalAgreement for checkout of rental tool
 */
public class Checkout {

    public RentalAgreement generateRentalAgreement(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws CheckoutException {
        validateArguments(rentalDayCount, discountPercent);

        Tool rentalTool = Tool.valueOf(toolCode);

        checkoutDate = formatDateToFullYear(checkoutDate);
        String dueDate = determineDueDate(checkoutDate, rentalDayCount);

        int chargeDays = ChargeDaysHandler.determineNumberOfChargeableDays(rentalTool.getToolType(), checkoutDate, dueDate);

        BigDecimal preDiscountCharge = ChargeCalculator.calculatePreDiscountCharge(chargeDays, rentalTool.getToolType().getDailyCharge());
        BigDecimal discountAmount = ChargeCalculator.calculateDiscountAmount(discountPercent, preDiscountCharge);
        BigDecimal finalCharge = ChargeCalculator.calculateFinalCharge(preDiscountCharge, discountAmount);

        return new RentalAgreement.Builder()
                .toolCode(toolCode)
                .toolType(rentalTool.getToolType())
                .toolBrand(rentalTool.getBrand())
                .rentalDays(rentalDayCount)
                .checkoutDate(formatDateToShortYear(checkoutDate))
                .dueDate(formatDateToShortYear(dueDate))
                .dailyRentalCharge(formatCurrencyString(rentalTool.getToolType().getDailyCharge()))
                .chargeDays(chargeDays)
                .preDiscountCharge(formatCurrencyString(preDiscountCharge.toString()))
                .discountPercent(String.valueOf(discountPercent) + "%")
                .discountAmount(formatCurrencyString(discountAmount.toString()))
                .finalCharge(formatCurrencyString(finalCharge.toString()))
                .build();
    }

    private void validateArguments(int rentalDayCount, int discountPercent) throws CheckoutException {
        if (rentalDayCount < 1) {
            throw new CheckoutException("Rental day count must be 1 or greater. rentalDayCount=" + rentalDayCount);
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new CheckoutException("Discount percent must be between 0-100. discountPercent=" + discountPercent);
        }
    }

    //year is inputted and outputted in shortened form, but need long form for date calculations/formatter
    private String formatDateToFullYear(String date) {
        String[] dateParts = date.split("/");
        return dateParts[0] + "/" + dateParts[1] + "/20" + dateParts[2];
    }

    private String formatDateToShortYear(String date) {
        String[] dateParts = date.split("/");
        return dateParts[0] + "/" + dateParts[1] + "/" + dateParts[2].substring(2);
    }

    private String determineDueDate(String checkoutDate, int rentalDayCount) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate checkoutLocalDate = LocalDate.parse(checkoutDate, dateTimeFormatter);
        LocalDate dueDateLocalDate = checkoutLocalDate.plusDays(rentalDayCount);

        return dueDateLocalDate.format(dateTimeFormatter);
    }

    //separate method in case currency/currency format changes - can just be updated here
    private String formatCurrencyString(String rawValue) {
        return "$" + rawValue;
    }
}
