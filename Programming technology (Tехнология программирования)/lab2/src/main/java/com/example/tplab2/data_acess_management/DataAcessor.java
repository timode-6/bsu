package com.example.tplab2.data_acess_management;

import com.example.tplab2.auxiliary_entities.Person;
import com.example.tplab2.auxiliary_entities.Date;
import com.example.tplab2.booking_management.BookingList;
import com.example.tplab2.booking_management.Booking;
import com.example.tplab2.login_management.User;
import com.example.tplab2.login_management.User.UserType;
import java.sql.*;

public class DataAcessor {
  public static class DataAcessException extends Exception {
    public DataAcessException(String errorMsg) {
      super(errorMsg);
    }
  }

  private static final String URL =
          "jdbc:postgresql://localhost:5432/hotel_db";
  private static final String user = "postgres";
  private static final String password = "7654321a";
  private boolean isConnectionValid = true;
  private Connection connection;
  private Statement statement;

  public DataAcessor() {
    try {
      connection = DriverManager.getConnection(URL, user, password);
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      isConnectionValid = false;
    }
  }

  public boolean isConnectionValid() {
    return isConnectionValid;
  }

  public boolean rollback() {
    try {
      connection.rollback();
    } catch (SQLException ex) {
      return false;
    }
    return true;
  }

  public void addBooking(Booking booking) throws SQLException {
    statement = connection.prepareStatement(
            SQLQueries.insertNewBookingQuery);
    PreparedStatement preparedStatement = ((PreparedStatement) statement);
    preparedStatement.setInt(1, booking.getAmountOfTenants());
    preparedStatement.setTimestamp(2, new Timestamp(
            booking.getCheckInTime().getTime()));
    preparedStatement.setTimestamp(3, new Timestamp(
            booking.getCheckOutTime().getTime()));
    Person creatorOfBooking = booking.getCreatorOfBooking();
    preparedStatement.setString(4, creatorOfBooking.getName());
    preparedStatement.setString(5, creatorOfBooking.getSurname());
    preparedStatement.setString(6, creatorOfBooking.getPhoneNumber());
    preparedStatement.setString(7, creatorOfBooking.getEmail());
    preparedStatement.setInt(8, booking.getRoomNum());
    preparedStatement.execute();
    connection.commit();
  }

  public UserType defineUserType(User user) throws SQLException {
    statement = connection.prepareStatement(SQLQueries.defineUserTypeQuery);
    PreparedStatement preparedStatement = ((PreparedStatement) statement);
    preparedStatement.setString(1, user.getNickname());
    preparedStatement.setString(2, user.getPassword());
    preparedStatement.execute();
    connection.commit();
    ResultSet resultSet = statement.getResultSet();
    if (!resultSet.next()) {
      return UserType.UNKNOWN;
    }
    String userTypeStr = resultSet.getString("user_type");
    UserType userType;
    if (userTypeStr.equals("manager")) {
      userType = UserType.MANAGER;
    } else if (userTypeStr.equals("receptionist")) {
      userType = UserType.RECEPTIONIST;
    } else {
      userType = UserType.ADMINISTRATOR;
    }
    return userType;
  }

  public void deleteBooking(int id) throws SQLException {
    statement = connection.prepareStatement(SQLQueries.deleteBookingQuery);
    PreparedStatement preparedStatement = ((PreparedStatement) statement);
    preparedStatement.setInt(1, id);
    preparedStatement.execute();
    connection.commit();
  }

  public void editBooking(Booking booking, int id) throws SQLException {
    deleteBooking(id);
    addBooking(booking);
  }

  public Booking getBooking(int id) throws SQLException, DataAcessException {
    statement = connection.prepareStatement(SQLQueries.getBookingQuery);
    PreparedStatement preparedStatement = ((PreparedStatement) statement);
    preparedStatement.setInt(1, id);
    preparedStatement.execute();
    connection.commit();
    ResultSet resultSet = statement.getResultSet();
    if (!resultSet.next()) {
      throw new DataAcessException("Booking with current id doesn't exist");
    }
    Person person = new Person(resultSet.getString(5), resultSet.getString(6),
            resultSet.getString(7), resultSet.getString(8));
    return new Booking(resultSet.getInt(2),
            new Date(resultSet.getTimestamp(3).toString()),
            new Date(resultSet.getTimestamp(4).toString()),
            person, resultSet.getInt(9));
  }

  public BookingList getBookingList() throws SQLException {
    statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(
            SQLQueries.getRowCountQuery);
    connection.commit();
    resultSet.next();
    String[][] bookingList = new String[resultSet.getInt(1)][4];
    statement = connection.createStatement();
    resultSet = statement.executeQuery(
            SQLQueries.getBookingListQuery);
    connection.commit();
    int i = 0;
    while (resultSet.next()) {
      for (int j = 0; j < 4; ++j) {
        bookingList[i][j] = resultSet.getString(j + 1);
      }
      bookingList[i][1] = bookingList[i][1].replaceFirst(":[^:]*$", "");
      bookingList[i][2] = bookingList[i][2].replaceFirst(":[^:]*$", "");
      ++i;
    }
    return new BookingList(bookingList);
  }
}