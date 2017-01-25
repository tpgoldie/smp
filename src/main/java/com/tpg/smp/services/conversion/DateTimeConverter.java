package com.tpg.smp.services.conversion;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class DateTimeConverter {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd-MM-yyyy");

    static String format(DateTime value) {
        return DATE_TIME_FORMATTER.print(value);
    }
}
