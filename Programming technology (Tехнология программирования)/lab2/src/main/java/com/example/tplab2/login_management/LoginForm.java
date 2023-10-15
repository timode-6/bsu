package com.example.tplab2.login_management;

import java.sql.SQLException;
import java.util.Scanner;
import com.example.tplab2.login_management.User.UserType;

public class LoginForm {
  private final LoginController m_loginController;
  private UserType m_lastUserType;

  //private final Stage m_mainStage;

  public LoginForm(/*Stage stage,*/ LoginController loginController) {
    //m_mainStage = stage;
    m_loginController = loginController;
  }

  public void login() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter nickname:");
    String nickname = scanner.nextLine();
    System.out.println("Enter password:");
    String password = scanner.nextLine();
    try {
      m_lastUserType = m_loginController.login(nickname, password);
      if (m_lastUserType == UserType.UNKNOWN) {
        System.out.println("User doesn't exist or incorrect password");
      }
    } catch (SQLException e) {
      if (m_loginController.databaseRollback()) {
        System.out.println("Incorrect query or connection problems");
        m_lastUserType = UserType.UNKNOWN;
      } else {
        System.out.println("Connection problems");
        System.exit(0);
      }
    }
  }

  public void show() {
    login();
  }

  public UserType getLastUserType() {
    return m_lastUserType;
  }
}