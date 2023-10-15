package com.example.tplab2.booking_management;

import com.example.tplab2.auxiliary_entities.Date;
import com.example.tplab2.auxiliary_entities.Person;

public class Booking {
  private final int m_amountOfTenants;
  private final Date m_checkInTime;
  private final Date m_checkOutTime;
  private final Person m_creatorOfBooking;
  private final int m_roomNum;

  public Booking(int amountOfTenants, Date checkInTime, Date checkOutTime,
                 Person creatorOfBooking, int roomNum) {
    m_amountOfTenants = amountOfTenants;
    m_checkInTime = checkInTime;
    m_checkOutTime = checkOutTime;
    m_creatorOfBooking = creatorOfBooking;
    m_roomNum = roomNum;
  }

  public int getAmountOfTenants() {
    return m_amountOfTenants;
  }

  public Date getCheckInTime() {
    return m_checkInTime;
  }

  public Date getCheckOutTime() {
    return m_checkOutTime;
  }

  public Person getCreatorOfBooking() {
    return m_creatorOfBooking;
  }

  public int getRoomNum() {
    return m_roomNum;
  }
}