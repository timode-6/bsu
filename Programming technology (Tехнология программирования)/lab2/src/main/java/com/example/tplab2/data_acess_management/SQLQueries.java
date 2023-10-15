package com.example.tplab2.data_acess_management;

public class SQLQueries {
  static final String insertNewBookingQuery = """
          INSERT INTO bookings (amount_of_tenants, check_in_time,
          check_out_time, client_name, client_surname, client_phone_number,
          client_email, room_number)
          VALUES
          (?, ?, ?, ?, ?, ?, ?, ?)""";

  static final String defineUserTypeQuery = """
          SELECT user_type FROM users
          WHERE (nickname = ? AND "password" = ?);""";

  static final String deleteBookingQuery = """
          DELETE FROM bookings WHERE booking_id = ?;""";

  static final String getBookingQuery = """
          SELECT * FROM bookings
          WHERE (booking_id = ?);""";

  static final String getRowCountQuery = """
          SELECT COUNT(*) FROM bookings""";

  static final String getBookingListQuery = """
          SELECT booking_id, check_in_time, check_out_time, room_number
          FROM bookings;""";
}

