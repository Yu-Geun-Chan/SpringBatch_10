package com.koreait.exam.springbatch_10.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static class date {
        public static int getEndDayof (int year, int month) {
//            String yearStr = year + "-";
//            String monthStr = month + "";
//
//            if (monthStr.length() == 1) {
//                monthStr = "0" + monthStr; // 1 ~ 9월의 앞에 0을 붙여주기 위해
//            }
//
//            String yearMonth = yearStr + monthStr;
            String yearMonth = year + "-" + "%02d".formatted(month);

            return getEndDayof(yearMonth);
        }

        public static int getEndDayof (String yearMonth) {
            LocalDate convertedDate = LocalDate.parse(yearMonth + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

            return convertedDate.getDayOfMonth();
        }

        public static LocalDateTime parse(String pattern, String textDate) {
            return LocalDateTime.parse(textDate, DateTimeFormatter.ofPattern(pattern));
        }
    }
}
