package com.example.tplab2.booking_management;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.example.tplab2.auxiliary_entities.Date;
import com.example.tplab2.auxiliary_entities.Person;
import com.example.tplab2.data_acess_management.DataAcessor;

public class BookingForm {
  /*private final Button addButton = new Button("Add booking");
  private final Button editButton = new Button("Edit booking");
  private final Button deleteButton = new Button("Delete booking");
  private final TableView<TableRowInfo> table = new TableView<>();*/
  /*private final Stage m_mainStage;
  private final Scene bookingFormScene;*/
  private final BookingController m_bookingController;
  private final Scanner scanner = new Scanner(System.in);

  private enum Operation {
    CREATE,
    EDIT,
    DELETE,
    NONE
  }

  private Operation currOperation = Operation.NONE;


  public BookingForm(/*Stage stage,*/BookingController bookingController) {
    m_bookingController = bookingController;
    //m_mainStage = stage;
    /*Pane root = new Pane();
    root.setStyle("-fx-background-color: yellow");
    GridPane grid = setGridPane();
    grid.setGridLinesVisible(true);
    grid.prefWidthProperty().bind(stage.widthProperty());
    grid.prefHeightProperty().bind(stage.heightProperty());
    root.getChildren().add(grid);
    bookingFormScene = new Scene(root);
    stage.setScene(bookingFormScene);
    stage.setTitle("Booking management");
    stage.setResizable(false);
    stage.show();*/
  }

  /*private GridPane setGridPane() {
    GridPane grid = new GridPane();
    //grid.setAlignment(Pos.CENTER);
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(5, 5, 5, 5));
    addButton.setOnAction(actionEvent -> createBooking());
    editButton.setOnAction(actionEvent -> editBooking());
    deleteButton.setOnAction(actionEvent -> deleteBooking());
    grid.add(addButton, 0, 0);
    grid.add(editButton, 1, 0);
    grid.add(deleteButton, 2, 0);
    //initTable();
    //grid.add(table, 0, 1, 3, 1);
    return grid;
  }*/

  /*void initButtons() {
    addButton.setPrefHeight(80);
    removeButton.setPrefHeight(80);
    updateButton.setPrefHeight(80);
    addButton.setPrefWidth(200);
    removeButton.setPrefWidth(200);
    updateButton.setPrefWidth(200);
    addButton.setOnAction(actionEvent -> {
      try {
        ResultSet resultSet = databaseManager.getListOfSubjects();
        StringBuilder content_text = new StringBuilder(addStudentTemplate);
        content_text.append("\nCurrent subjects:");
        int amountOfSubjects = 0;
        while (resultSet.next()) {
          ++amountOfSubjects;
          content_text.append(" \"").append(resultSet.getString(
                  "subject_name")).append('\"');
          if (amountOfSubjects % 3 == 0) {
            content_text.append("\n");
          }
        }
        addStudentDialog.setContentText(content_text.toString());
        Optional<String> res = addStudentDialog.showAndWait();
        if (res.isEmpty()) {
          return;
        }
        String[] newStudentInfo = res.get().split(" ");
        if (newStudentInfo.length != 3 + amountOfSubjects) {
          throw new IllegalArgumentException();
        }
        databaseManager.addNewStudent(newStudentInfo);
      } catch (SQLException e) {
        SQLExceptionHandler();
      } catch (IllegalArgumentException e) {
        alert.setContentText("Incorrect query");
        alert.showAndWait();
      }
    });
    removeButton.setOnAction(actionEvent -> {
      try {
        Optional<String> res = removeStudentDialog.showAndWait();
        if (res.isEmpty()) {
          return;
        }
        String[] studentInfo = res.get().split(" ");
        if (studentInfo.length != 3) {
          throw new IllegalArgumentException();
        }
        databaseManager.removeStudent(studentInfo);
      } catch (SQLException e) {
        SQLExceptionHandler();
      } catch (IllegalArgumentException e) {
        alert.setContentText("Incorrect query");
        alert.showAndWait();
      }
    });
    updateButton.setOnAction(actionEvent -> {
      try {
        ResultSet resultSet = databaseManager.getStatistics();
        resultSet.next();
        String resultsRecord = resultSet.getString("get_session_results");
        String[] sessionResults = resultsRecord.replaceAll("[()]", "").
                split(",");
        table.setItems(FXCollections.observableArrayList(
                new TableRowInfo(Integer.parseInt(sessionResults[0]),
                        Integer.parseInt(sessionResults[1]),
                        Integer.parseInt(sessionResults[2]))));
      } catch (SQLException e) {
        SQLExceptionHandler();
      }
    });
  }*/

  /*void initTable() {
   *//*table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setPrefHeight(50);*//*
    TableColumn<TableRowInfo, Integer> column1 = new TableColumn<>("Id");
    column1.setCellValueFactory(new PropertyValueFactory<>("id"));
    table.getColumns().add(column1);
    TableColumn<TableRowInfo, String> column2 =
            new TableColumn<>("Check in time");
    column2.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
    table.getColumns().add(column2);
    TableColumn<TableRowInfo, String> column3 =
            new TableColumn<>("Check out time");
    column3.setCellValueFactory(
            new PropertyValueFactory<>("checkOutTime"));
    table.getColumns().add(column3);
  }*/

  void SQLExeptionHandler() {
    if (m_bookingController.databaseRollback()) {
      System.out.println("Incorrect query or connection problems");
      currOperation = Operation.NONE;
    } else {
      System.out.println("Connection problems");
      System.exit(0);
    }
  }

  void IncorrectInputExceptionHandler() {
    System.out.println("Incorrect input. Try again");
    currOperation = Operation.NONE;
  }

  public void show() {
    System.out.println("YOU ENTERED BOOKING MANAGEMENT");
    int i = 0;
    cycle:
    while (true) {
      System.out.println("""
              Choose operation:
              1. Create booking
              2. Edit booking
              3. Delete booking
              4. Exit
              """);
      try {
        int operationNum = scanner.nextInt();
        scanner.skip("\n");
        switch (operationNum) {
          case 1 -> createBooking();
          case 2 -> editBooking();
          case 3 -> deleteBooking();
          case 4 -> {
            break cycle;
          }
          default -> throw new IllegalArgumentException();
        }
      } catch (NoSuchElementException | IllegalArgumentException e) {
        IncorrectInputExceptionHandler();
        scanner.nextLine();
      } catch (SQLException e) {
        System.out.println(e.getMessage() + " " + e.getSQLState());
        SQLExeptionHandler();
      } catch (DataAcessor.DataAcessException e) {
        System.out.println(e.getMessage());
        currOperation = Operation.NONE;
      }
    }
  }

  private void createBooking() throws SQLException,
          DataAcessor.DataAcessException {
    currOperation = Operation.CREATE;
    m_bookingController.getBookingListFromDB();
  }

  private void editBooking() throws SQLException,
          DataAcessor.DataAcessException {
    currOperation = Operation.EDIT;
    m_bookingController.getBookingListFromDB();
  }

  private void deleteBooking() throws SQLException,
          DataAcessor.DataAcessException {
    currOperation = Operation.DELETE;
    m_bookingController.getBookingListFromDB();
  }

  private void acceptBookingData() throws SQLException {
    System.out.println("""
            Enter booking data in next order
            Amount of tenants
            Check in time in form YYYY-MM-DD HH:MM
            Check out time in form YYYY-MM-DD HH:MM
            Client name
            Client surname
            Client phone number
            Client email (just press enter if there is no email)
            Room number""");
    int amountOfTenants = scanner.nextInt();
    scanner.skip("\n");
    Date checkInTime = new Date(scanner.nextLine());
    Date checkOutTime = new Date(scanner.nextLine());
    String client_name = scanner.nextLine();
    String client_surname = scanner.nextLine();
    String cliemt_phone_number = scanner.nextLine();
    String email = scanner.nextLine();
    Person person;
    if (email.isEmpty()) {
      person = new Person(client_name, client_surname, cliemt_phone_number);
    } else {
      person = new Person(client_name, client_surname, cliemt_phone_number,
              email);
    }
    Booking newBooking = new Booking(amountOfTenants, checkInTime,
            checkOutTime, person, scanner.nextInt());
    scanner.skip("\n");
    m_bookingController.createBooking(newBooking);
  }

  private void acceptBookingToDelete(int id) throws SQLException {
    m_bookingController.deleteBooking(id);
  }

  private void saveEditedData() throws SQLException {
    System.out.println("""
            Enter booking data in next order
            Amount of tenants
            Check in time in form YYYY-MM-DD HH:MM
            Check out time in form YYYY-MM-DD HH:MM
            Client name
            Client surname
            Client phone number
            Client email (just press enter if there is no email)
            Room number""");
    int amountOfTenants = scanner.nextInt();
    scanner.skip("\n");
    Date checkInTime = new Date(scanner.nextLine());
    Date checkOutTime = new Date(scanner.nextLine());
    String client_name = scanner.nextLine();
    String client_surname = scanner.nextLine();
    String cliemt_phone_number = scanner.nextLine();
    String email = scanner.nextLine();
    Person person;
    if (email.isEmpty()) {
      person = new Person(client_name, client_surname, cliemt_phone_number);
    } else {
      person = new Person(client_name, client_surname, cliemt_phone_number,
              email);
    }
    Booking newBooking = new Booking(amountOfTenants, checkInTime,
            checkOutTime, person, scanner.nextInt());
    scanner.skip("\n");
    m_bookingController.editBooking(newBooking);
  }

  private void selectBooking(int id) throws DataAcessor.DataAcessException,
          SQLException {
    m_bookingController.getBooking(id);
  }

  public void showBooking(Booking booking) throws SQLException {
    StringBuilder stringBuilder = new StringBuilder("Amount of tenants: ");
    stringBuilder.append(booking.getAmountOfTenants()).append('\n');
    stringBuilder.append("Check in time: ").append(
            booking.getCheckInTime().toString()).append('\n');
    stringBuilder.append("Check out time: ").append(
            booking.getCheckOutTime().toString()).append('\n');
    Person client = booking.getCreatorOfBooking();
    stringBuilder.append("Client is ").append(client.getName()).append(' ')
            .append(client.getSurname()).append('\n');
    stringBuilder.append("Phone number: ").append(client.getPhoneNumber())
            .append('\n');
    if (client.getEmail() != null) {
      stringBuilder.append("Email: ").append(client.getEmail())
              .append('\n');
    }
    stringBuilder.append("Room number: ").append(booking.getRoomNum())
            .append('\n');
    System.out.println(stringBuilder);
    saveEditedData();
  }

  public void showBookingList(BookingList bookingList) throws
          DataAcessor.DataAcessException, SQLException {
    System.out.println("Current list of bookings:\nId\tCheck in time" +
            "\t\tCheck out time\t\tRoom number");
    for (int i = 0; i < bookingList.getAmountOfRows(); ++i) {
      for (int j = 0; j < 4; ++j) {
        System.out.print(bookingList.get(i, j) + "\t");
      }
      System.out.println();
    }
    switch (currOperation) {
      case CREATE -> acceptBookingData();
      case EDIT -> {
        System.out.println("Enter booking id");
        int id;
        id = scanner.nextInt();
        scanner.skip("\n");
        selectBooking(id);
      }
      case DELETE -> {
        System.out.println("Enter booking id");
        int id;
        id = scanner.nextInt();
        scanner.skip("\n");
        acceptBookingToDelete(id);
      }
    }
  }

  /*public static class TableRowInfo {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty checkInTime;
    private final SimpleStringProperty checkOutTime;

    public TableRowInfo(int id, String checkInTime, String checkOutTime) {
      this.id = new SimpleIntegerProperty(id);
      this.checkInTime = new SimpleStringProperty(checkInTime);
      this.checkOutTime = new SimpleStringProperty(checkOutTime);
    }

    public final IntegerProperty idProperty() {
      return id;
    }

    public final StringProperty checkInTimeProperty() {
      return checkInTime;
    }

    public final StringProperty checkOutTimeProperty() {
      return checkOutTime;
    }
  }*/
}