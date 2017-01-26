package com.tpg.smp.services.conversion;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateMonthYearFormat {
    private static DateMonthYearFormat DATE_MONTH_YEAR_FORMAT = new DateMonthYearFormat();

    public static DateMonthYearFormat getDateMonthYearFormat() { return DATE_MONTH_YEAR_FORMAT; }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    public DateTimeFormatter getDateTimeFormatter() { return dateTimeFormatter; }
}
