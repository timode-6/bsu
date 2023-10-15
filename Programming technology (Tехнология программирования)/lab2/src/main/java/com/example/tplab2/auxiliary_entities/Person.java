package com.example.tplab2.auxiliary_entities;

public class Person {
  private final String m_name;
  private final String m_surname;
  private final String m_phoneNumber;
  private final String m_email;

  public Person(String name, String surname, String phoneNumber,
                String email) {
    m_name = name;
    m_surname = surname;
    m_phoneNumber = phoneNumber;
    m_email = email;
  }

  public Person(String name, String surname, String phoneNumber) {
    this(name, surname, phoneNumber, null);
  }

  public String getName() {
    return m_name;
  }

  public String getSurname() {
    return m_surname;
  }

  public String getPhoneNumber() {
    return m_phoneNumber;
  }

  public String getEmail() {
    return m_email;
  }
}
