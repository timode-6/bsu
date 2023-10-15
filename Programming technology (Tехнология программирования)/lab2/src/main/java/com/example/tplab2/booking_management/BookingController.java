package com.example.tplab2.booking_management;

import com.example.tplab2.data_acess_management.DataAcessor;
import java.sql.SQLException;

public class BookingController {
  private final BookingForm m_bookingForm;
  private final DataAcessor m_dataAccessor;
  private int bookingIdToEdit;

  public BookingController(/*Stage stage,*/ DataAcessor dataAcessor) {
    m_bookingForm = new BookingForm(/*stage,*/ this);
    m_dataAccessor = dataAcessor;
  }

  public void start() {
    m_bookingForm.show();
  }

  public void getBooking(int id) throws SQLException,
          DataAcessor.DataAcessException {
    bookingIdToEdit = id;
    m_bookingForm.showBooking(m_dataAccessor.getBooking(id));
  }

  public void createBooking(Booking booking) throws SQLException {
    m_dataAccessor.addBooking(booking);
  }

  public void deleteBooking(int id) throws SQLException {
    m_dataAccessor.deleteBooking(id);
  }

  public void editBooking(Booking booking) throws SQLException {
    m_dataAccessor.editBooking(booking, bookingIdToEdit);
  }

  public void getBookingListFromDB() throws SQLException,
          DataAcessor.DataAcessException {
    m_bookingForm.showBookingList(m_dataAccessor.getBookingList());
  }

  public boolean databaseRollback() {
    return m_dataAccessor.rollback();
  }
}