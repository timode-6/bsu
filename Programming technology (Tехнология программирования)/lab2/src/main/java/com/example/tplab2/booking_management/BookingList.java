package com.example.tplab2.booking_management;

public class BookingList {
  private final String[][] m_bookingList;

  public BookingList(String[][] bookingList) {
    m_bookingList = bookingList;
  }

  public String get(int i, int j) {
    if (m_bookingList == null || i >= m_bookingList.length ||
            m_bookingList[0] == null || j >= m_bookingList[0].length) {
      throw new IllegalArgumentException("Incorrect indexes for this " +
              "booking list");
    }
    return m_bookingList[i][j];
  }

  public int getAmountOfRows() {
    return m_bookingList.length;
  }
}