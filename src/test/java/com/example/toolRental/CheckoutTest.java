package com.example.toolRental;

import com.example.toolRental.enums.ToolType;
import org.junit.Assert;
import org.junit.Test;

public class CheckoutTest {

    Checkout checkout = new Checkout();

    @Test(expected = CheckoutException.class)
    public void shouldThrowExceptionForDiscountTooHigh_Test1() throws CheckoutException {

        //given
        String toolCode = "JAKR";
        String checkoutDate = "9/3/15";
        int rentalDays = 5;

        //and discount is over 100
        int discountPercent = 101;

        //when
        checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then a CheckoutException is thrown
    }

    @Test
    public void shouldGenerateProperRentalAgreement_Test2() throws CheckoutException {

        //given
        String toolCode = "LADW";
        String checkoutDate = "7/2/20";
        int rentalDays = 3;
        int discountPercent = 10;

        //when
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then
        Assert.assertEquals("7/5/20", rentalAgreement.getDueDate());
        Assert.assertEquals("$1.99", rentalAgreement.getDailyRentalCharge());
        Assert.assertEquals(2, rentalAgreement.getChargeDays());
        Assert.assertEquals("$3.98", rentalAgreement.getPreDiscountCharge());
        Assert.assertEquals("10%", rentalAgreement.getDiscountPercent());
        Assert.assertEquals("$0.40", rentalAgreement.getDiscountAmount());
        Assert.assertEquals("$3.58", rentalAgreement.getFinalCharge());

        Assert.assertEquals("LADW", rentalAgreement.getToolCode());
        Assert.assertEquals(ToolType.LADDER, rentalAgreement.getToolType());
        Assert.assertEquals("Werner", rentalAgreement.getToolBrand());
        Assert.assertEquals(3, rentalAgreement.getRentalDays());
        Assert.assertEquals("7/2/20", rentalAgreement.getCheckoutDate());

    }

    @Test
    public void shouldGenerateProperRentalAgreement_Test3() throws CheckoutException {

        //given
        String toolCode = "CHNS";
        String checkoutDate = "7/2/15";
        int rentalDays = 5;
        int discountPercent = 25;

        //when
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then
        Assert.assertEquals("7/7/15", rentalAgreement.getDueDate());
        Assert.assertEquals("$1.49", rentalAgreement.getDailyRentalCharge());
        Assert.assertEquals(3, rentalAgreement.getChargeDays());
        Assert.assertEquals("$4.47", rentalAgreement.getPreDiscountCharge());
        Assert.assertEquals("25%", rentalAgreement.getDiscountPercent());
        Assert.assertEquals("$1.12", rentalAgreement.getDiscountAmount());
        Assert.assertEquals("$3.35", rentalAgreement.getFinalCharge());

        Assert.assertEquals("CHNS", rentalAgreement.getToolCode());
        Assert.assertEquals(ToolType.CHAINSAW, rentalAgreement.getToolType());
        Assert.assertEquals("Stihl", rentalAgreement.getToolBrand());
        Assert.assertEquals(5, rentalAgreement.getRentalDays());
        Assert.assertEquals("7/2/15", rentalAgreement.getCheckoutDate());
    }

    @Test
    public void shouldGenerateProperRentalAgreement_Test4() throws CheckoutException {

        //given
        String toolCode = "JAKD";
        String checkoutDate = "9/3/15";
        int rentalDays = 6;
        int discountPercent = 0;

        //when
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then
        Assert.assertEquals("9/9/15", rentalAgreement.getDueDate());
        Assert.assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        Assert.assertEquals(3, rentalAgreement.getChargeDays());
        Assert.assertEquals("$8.97", rentalAgreement.getPreDiscountCharge());
        Assert.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assert.assertEquals("$0.00", rentalAgreement.getDiscountAmount());
        Assert.assertEquals("$8.97", rentalAgreement.getFinalCharge());

        Assert.assertEquals("JAKD", rentalAgreement.getToolCode());
        Assert.assertEquals(ToolType.JACKHAMMER, rentalAgreement.getToolType());
        Assert.assertEquals("DeWalt", rentalAgreement.getToolBrand());
        Assert.assertEquals(6, rentalAgreement.getRentalDays());
        Assert.assertEquals("9/3/15", rentalAgreement.getCheckoutDate());
    }

    @Test
    public void shouldGenerateProperRentalAgreement_Test5() throws CheckoutException {

        //given
        String toolCode = "JAKR";
        String checkoutDate = "7/2/15";
        int rentalDays = 9;
        int discountPercent = 0;

        //when
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then
        Assert.assertEquals("7/11/15", rentalAgreement.getDueDate());
        Assert.assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        Assert.assertEquals(5, rentalAgreement.getChargeDays());
        Assert.assertEquals("$14.95", rentalAgreement.getPreDiscountCharge());
        Assert.assertEquals("0%", rentalAgreement.getDiscountPercent());
        Assert.assertEquals("$0.00", rentalAgreement.getDiscountAmount());
        Assert.assertEquals("$14.95", rentalAgreement.getFinalCharge());

        Assert.assertEquals("JAKR", rentalAgreement.getToolCode());
        Assert.assertEquals(ToolType.JACKHAMMER, rentalAgreement.getToolType());
        Assert.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assert.assertEquals(9, rentalAgreement.getRentalDays());
        Assert.assertEquals("7/2/15", rentalAgreement.getCheckoutDate());
    }

    @Test
    public void shouldGenerateProperRentalAgreement_Test6() throws CheckoutException {

        //given
        String toolCode = "JAKR";
        String checkoutDate = "7/2/20";
        int rentalDays = 4;
        int discountPercent = 50;

        //when
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement(toolCode, rentalDays, discountPercent, checkoutDate);

        //then
        Assert.assertEquals("7/6/20", rentalAgreement.getDueDate());
        Assert.assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        Assert.assertEquals(1, rentalAgreement.getChargeDays());
        Assert.assertEquals("$2.99", rentalAgreement.getPreDiscountCharge());
        Assert.assertEquals("50%", rentalAgreement.getDiscountPercent());
        Assert.assertEquals("$1.50", rentalAgreement.getDiscountAmount());
        Assert.assertEquals("$1.49", rentalAgreement.getFinalCharge());

        Assert.assertEquals("JAKR", rentalAgreement.getToolCode());
        Assert.assertEquals(ToolType.JACKHAMMER, rentalAgreement.getToolType());
        Assert.assertEquals("Ridgid", rentalAgreement.getToolBrand());
        Assert.assertEquals(4, rentalAgreement.getRentalDays());
        Assert.assertEquals("7/2/20", rentalAgreement.getCheckoutDate());
    }
}