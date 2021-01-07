package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000 , 1, 1);

    public static LocalDate parse(String date){
        LocalDate localDate =  LocalDate.parse(date,  DateTimeFormatter.ofPattern("ddMMyyyy"));
        return of(localDate.getYear(), localDate.getMonth());
    }

    public static LocalDate of(int year, Month month){
        return LocalDate.of(year,month,1);
    }
}
