package com.tpg.smp.services.conversion;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

public class ToDateTimeConverter extends DateTimeConverter implements Converter<String, DateTime> {
    @Override
    public DateTime convert(String source) {
        return DATE_MONTH_YEAR_FORMAT.getDateTimeFormatter().parseDateTime(source);
    }
}
