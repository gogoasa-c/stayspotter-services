package com.gogoasa.c.data.util;

import java.time.ZoneId;
import java.util.Date;

public class Helper {
    public static String convertDateToLocalDate(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .toString();
    }
}
