package org.cryptodepositbot;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static String getMoscowDateTime(LocalDateTime localDateTime){
        ZonedDateTime moscowTime = localDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        return moscowTime.format(dateTimeFormatter);
    }


}
