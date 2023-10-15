package com.example.tplab2;

import com.example.tplab2.booking_management.BookingController;
import com.example.tplab2.data_acess_management.DataAcessor;
import com.example.tplab2.login_management.LoginController;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main /*extends Application*/ {
  /*private final Alert alert = new Alert(Alert.AlertType.ERROR);
  private BookingController m_bookingController;
  private LoginController m_loginController;
  private final DataAcessor m_dataAcessor = new DataAcessor();*/

  /*{
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Database connection problems");
  }*/

  /*@Override
  public void start(Stage stage) {
    if (!m_dataAcessor.isConnectionValid()) {
      alert.showAndWait();
      System.exit(0);
    }
    stage.setWidth(500);
    stage.setHeight(500);
    m_bookingController = new BookingController(stage, m_dataAcessor);
    m_loginController = new LoginController(stage, m_dataAcessor);
  }*/

  public static void main(String[] args) {
    /*Scanner scanner = new Scanner(System.in);
    System.out.println(scanner.nextInt());
    scanner.skip("\n");
    System.out.println(scanner.nextLine());
    System.out.println(scanner.nextLine());*/

    DataAcessor dataAcessor = new DataAcessor();
    if (!dataAcessor.isConnectionValid()) {
      System.out.println("Database connection problems");
      System.exit(0);
    }
    BookingController bookingController = new BookingController(dataAcessor);
    LoginController loginController = new LoginController(dataAcessor);
    Scanner scanner = new Scanner(System.in);
    outer:
    while (true) {
      loginController.start();
      switch (loginController.getLastUserType()) {
        case MANAGER -> bookingController.start();
        case ADMINISTRATOR, RECEPTIONIST -> System.out.println(
                "No implementation");
      }
      while (true) {
        System.out.println("Login again or exit?\n1. Login\n2. Exit");
        try {
          int answer = scanner.nextInt();
          scanner.skip("\n");
          if (answer == 1) {
            continue outer;
          } else if (answer == 2) {
            break outer;
          } else {
            throw new IllegalArgumentException();
          }
        } catch (NoSuchElementException | IllegalArgumentException e) {
          System.out.println("Incorrect input");
        }
      }
    }
    //launch();
  }
}