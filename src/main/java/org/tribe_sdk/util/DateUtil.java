package org.tribe_sdk.util;

import org.joda.time.DateTimeZone;

import java.util.Date;

public class DateUtil {
    public static Date convertToUTC(Date date) {
        DateTimeZone d = DateTimeZone.getDefault();
        return new Date(d.convertLocalToUTC(date.getTime(), false));
    }
}
