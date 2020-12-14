package org.rga.mapper;

import org.rga.mapper.qualifiers.DateConverter;
import org.rga.mapper.qualifiers.ParseDate;
import org.rga.mapper.qualifiers.ParseTimestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DateConverter
public class DateToTimestamp {
    private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @ParseDate
    public String parseDate(Date date) {
        String value = "";
        if(date !=null) {
            value = DATE_FORMAT.format(date);
        }
        return value;
    }

    @ParseTimestamp
    public Date parseTimestamp(Date timestamp) {
        String value = "";
        if(timestamp != null) {
            try {
                value = DATE_TIME_FORMAT.format(timestamp);
                return DATE_TIME_FORMAT.parse(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}
