package com.tpg.smp.services.conversion;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

public class FromDateTimeConverter extends DateTimeConverter implements Converter<DateTime, String> {
    @Override
    public String convert(DateTime source) {
        return DATE_MONTH_YEAR_FORMAT.getDateTimeFormatter().print(source);
    }
}
