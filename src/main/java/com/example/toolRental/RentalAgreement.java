package com.example.toolRental;

import com.example.toolRental.enums.ToolType;

/*
Rental Agreement - uses Builder Pattern to construct

Note: I actually have some reservations about the use of the Builder Pattern. When an object has a lot of parameters, it can
make the code more understandable by replacing large constructors with explicit setter methods and help prevent arguments
with the same type from getting mixed up. That's why I chose to use it here (and maybe to show off for the demo.... :-) )
However, care must be taken when instantiating the object using the Builder - unlike with a normal constructor, an object
can be created without a required property if someone forgot to call one of the Builder methods.
 */

public class RentalAgreement {
    private String toolCode;
    private ToolType toolType;
    private String toolBrand;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private int chargeDays;
    private String preDiscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    private RentalAgreement() {
    }

    public static class Builder {
        private String toolCode;
        private ToolType toolType;
        private String toolBrand;
        private int rentalDays;
        private String checkoutDate;
        private String dueDate;
        private String dailyRentalCharge;
        private int chargeDays;
        private String preDiscountCharge;
        private String discountPercent;
        private String discountAmount;
        private String finalCharge;

        public Builder() {
        }

        public Builder toolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public Builder toolType(ToolType toolType) {
            this.toolType = toolType;
            return this;
        }

        public Builder toolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public Builder rentalDays(int rentalDays) {
            this.rentalDays = rentalDays;
            return this;
        }

        public Builder checkoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public Builder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder dailyRentalCharge(String dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public Builder chargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public Builder preDiscountCharge(String preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public Builder discountPercent(String discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder discountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder finalCharge(String finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        public RentalAgreement build() {
            RentalAgreement rentalAgreement = new RentalAgreement();
            rentalAgreement.toolCode = this.toolCode;
            rentalAgreement.toolType = this.toolType;
            rentalAgreement.toolBrand = this.toolBrand;
            rentalAgreement.rentalDays = this.rentalDays;
            rentalAgreement.checkoutDate = this.checkoutDate;
            rentalAgreement.dueDate = this.dueDate;
            rentalAgreement.dailyRentalCharge = this.dailyRentalCharge;
            rentalAgreement.chargeDays = this.chargeDays;
            rentalAgreement.preDiscountCharge = this.preDiscountCharge;
            rentalAgreement.discountPercent = this.discountPercent;
            rentalAgreement.discountAmount = this.discountAmount;
            rentalAgreement.finalCharge = this.finalCharge;

            return rentalAgreement;
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getFinalCharge() {
        return finalCharge;
    }

}
