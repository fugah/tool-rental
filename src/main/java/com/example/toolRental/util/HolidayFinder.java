package com.example.toolRental.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

/*
Helper class to determine whether rental period contains any holidays
 */
public class HolidayFinder {

    public static int findNumberOfHolidaysInDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        int startYear = startLocalDate.getYear();
        int endYear = endLocalDate.getYear();

        int numberOfHolidays = 0;
        //loop through years to account for possible long-term rentals that span multiple years and multiple holidays
        for (int year = startYear; year <= endYear; year++) {
            LocalDate laborDay = determineLaborDayForYear(year);
            if (laborDay.isAfter(startLocalDate) && laborDay.isBefore(endLocalDate)) {
                numberOfHolidays++;
            }

            LocalDate independenceDay = determineIndependenceDayForYear(year);
            if (independenceDay.isAfter(startLocalDate) && independenceDay.isBefore(endLocalDate)) {
                numberOfHolidays++;
            }
        }

        return numberOfHolidays;
    }

    //labor day = first monday of sept for specified year
    private static LocalDate determineLaborDayForYear(int year) {
        LocalDate septDate = LocalDate.of(year, Month.SEPTEMBER, 1);
        return septDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    //independence day = 4th of july if weekday, 3rd of july if saturday, 5th of july if sunday
    private static LocalDate determineIndependenceDayForYear(int year) {
        LocalDate fourthOfJuly = LocalDate.of(year, Month.JULY, 4);
        if (fourthOfJuly.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return fourthOfJuly.minusDays(1);
        }
        else if (fourthOfJuly.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return fourthOfJuly.plusDays(1);
        }
        return fourthOfJuly;
    }
}
