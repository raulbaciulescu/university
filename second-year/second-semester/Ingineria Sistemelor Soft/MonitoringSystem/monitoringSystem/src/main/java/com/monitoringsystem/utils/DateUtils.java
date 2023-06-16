package com.monitoringsystem.utils;

import com.sun.istack.NotNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateUtils {

    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    @NotNull
    public static String stringifyLocalTime(final @NotNull LocalTime localTime) {
        return dateTimeFormatter.format(localTime);
    }
}
