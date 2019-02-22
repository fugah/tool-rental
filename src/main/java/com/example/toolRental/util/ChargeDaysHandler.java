package com.example.toolRental.util;

import com.example.toolRental.enums.ToolType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
Helper class to get the number of chargeable days in rental period
 */
public class ChargeDaysHandler {

    public static int determineNumberOfChargeableDays(ToolType toolType, String startDate, String endDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate startLocalDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, dateTimeFormatter);

        int numberOfWeekdays = 0;
        int numberOfWeekendDays = 0;

        LocalDate date = startLocalDate.plusDays(1); //no charge for first day

        //loop through the rental period to get number of weekdays and weekend days
        while (date.isBefore(endLocalDate.plusDays(1))) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                numberOfWeekendDays++;
            }
            else {
                numberOfWeekdays++;
            }
            date = date.plusDays(1);
        }

        //add up the total number of chargeable days based on the tool type's charge properties
        int numberOfChargeableDays = 0;
        if (toolType.isWeekdayCharge()) {
            int numberOfHolidays = 0;

            //holidays are only on weekdays, so if tool type doesn't charge on holidays, subtract from total weekdays
            if (!toolType.isHolidayCharge()) {
                numberOfHolidays = HolidayFinder.findNumberOfHolidaysInDateRange(startLocalDate, endLocalDate);
            }

            numberOfChargeableDays = numberOfChargeableDays + numberOfWeekdays - numberOfHolidays;
        }

        if (toolType.isWeekendCharge()) {
            numberOfChargeableDays = numberOfChargeableDays + numberOfWeekendDays;
        }

        return numberOfChargeableDays;
    }
}
