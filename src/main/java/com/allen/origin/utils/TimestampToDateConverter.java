package com.allen.origin.utils;

import com.hd123.rumba.commons.util.converter.ConversionException;
import com.hd123.rumba.commons.util.converter.Converter;

import java.util.Date;

public class TimestampToDateConverter implements Converter<Long, Date> {
    private static final TimestampToDateConverter INSTANCE = new TimestampToDateConverter();

    private TimestampToDateConverter() {
    }

    public static TimestampToDateConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public Date convert(Long src) throws ConversionException {
        return new Date(src);
    }
}
