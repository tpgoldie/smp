package com.tpg.smp.services.conversion;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class DateTimeConverter {
    static final DateMonthYearFormat DATE_MONTH_YEAR_FORMAT = DateMonthYearFormat.getDateMonthYearFormat();

    static String format(DateTime value) {
        return DATE_MONTH_YEAR_FORMAT.getDateTimeFormatter().print(value);
    }
}
