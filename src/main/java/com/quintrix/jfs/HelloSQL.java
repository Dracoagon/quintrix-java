package com.quintrix.jfs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HelloSQL {
  /*
   * Testing connection to a mysql server
   * 
   */
  public static void main(String[] argv) {

    System.out.println("-------- MySQL JDBC Connection Demo ------------");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found !!");
      return;
    }
    System.out.println("MySQL JDBC Driver Registered!");

    // get username and password
    LoginCredentials login = new LoginCredentials();
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your username: ");
    login.setUsername(sc.next());
    System.out.println("Enter your password (type 'none' if no password): ");
    String password = sc.next();
    if (password != "none") {
      login.setPassword(password);
    } else {
      login.setPassword("");
    }
    sc.close();

    // connect to server
    Connection connection = null;
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCDemo",
          login.getUsername(), login.getPassword());
      System.out.println("SQL Connection to database established!");

    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      return;
    } finally {
      try {
        if (connection != null)
          connection.close();
        System.out.println("Connection closed !!");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
