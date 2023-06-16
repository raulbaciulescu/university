package com.example.socnet.domain.util;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static long fromDayAndMonth(final int day,
                                       final @NotNull String month) {
        return Date.from(
                LocalDate.of(2022, getMonthInt(month), day)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant())
                .getTime();
    }

    private static Month getMonthInt(final @NotNull String month) {
        return switch (month) {
            case "February" -> Month.FEBRUARY;
            case "March" -> Month.MARCH;
            case "April" -> Month.APRIL;
            case "May" -> Month.MAY;
            case "June" -> Month.JUNE;
            case "July" -> Month.JULY;
            case "August" -> Month.AUGUST;
            case "September" -> Month.SEPTEMBER;
            case "October" -> Month.OCTOBER;
            case "November" -> Month.NOVEMBER;
            case "December" -> Month.DECEMBER;
            default -> Month.JANUARY;
        };
    }
}
