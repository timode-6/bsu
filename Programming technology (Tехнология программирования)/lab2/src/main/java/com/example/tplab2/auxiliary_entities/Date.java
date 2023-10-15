package com.example.tplab2.auxiliary_entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
  private final int m_year;
  private final int m_month;
  private final int m_day;
  private final int m_hour;
  private final int m_minute;
  private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31,
          30, 31, 30, 31};

  public Date(int year, int month, int day, int hour, int minute) {
    if (year < 0 || month <= 0 || month > 12 || day <= 0 || hour < 0 ||
            hour >= 24 || minute < 0 || minute >= 60) {
      throw new IllegalArgumentException("Incorrect date");
    }
    if (month == 2 && year % 4 == 0 && day >= 30) {
      throw new IllegalArgumentException("Incorrect date");
    }
    if (day > daysInMonth[month - 1]) {
      throw new IllegalArgumentException("Incorrect date");
    }
    m_year = year;
    m_month = month;
    m_day = day;
    m_hour = hour;
    m_minute = minute;
  }

  public Date(int year, int month, int day, int hour) {
    this(year, month, day, hour, 0);
  }

  public Date(String strWithDate) {
    String[] dateInfoArr = strWithDate.split("[ :-]");
    if (dateInfoArr.length < 5 || dateInfoArr.length > 6) {
      throw new IllegalArgumentException("Incorrect date");
    }
    m_year = Integer.parseInt(dateInfoArr[0]);
    m_month = Integer.parseInt(dateInfoArr[1]);
    m_day = Integer.parseInt(dateInfoArr[2]);
    m_hour = Integer.parseInt(dateInfoArr[3]);
    m_minute = Integer.parseInt(dateInfoArr[4]);
  }

  public Long getTime() {
    java.util.Date parsedDate;
    try {
      SimpleDateFormat simpleDateFormat =
              new SimpleDateFormat("yyyy.MM.dd HH:mm");
      parsedDate = simpleDateFormat.parse(toString());
    } catch (ParseException ignored) {
      return null;
    }
    return parsedDate.getTime();
  }

  @Override
  public String toString() {
    return String.valueOf(m_year) + '.' + m_month + '.' +
            m_day + ' ' + m_hour + ':' +
            m_minute;
  }
}
