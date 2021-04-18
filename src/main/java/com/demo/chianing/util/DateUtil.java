package com.demo.chianing.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat defaultFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);

    public static String getDefaultFormatDate(long time) {
        return getDefaultFormatDate(new Date(time));
    }

    public static String getDefaultFormatDate(Date date) {
        return Optional.ofNullable(date)
                .map(defaultFormat::format)
                .orElse(null);
    }

}
