module com.example.tplab2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  opens com.example.tplab2 to javafx.fxml;
  exports com.example.tplab2;
  exports com.example.tplab2.booking_management;
  opens com.example.tplab2.booking_management to javafx.fxml;
  exports com.example.tplab2.data_acess_management;
  opens com.example.tplab2.data_acess_management to javafx.fxml;
  exports com.example.tplab2.login_management;
  opens com.example.tplab2.login_management to javafx.fxml;
  exports com.example.tplab2.auxiliary_entities;
}