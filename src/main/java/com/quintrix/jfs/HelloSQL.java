package com.quintrix.jfs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

      /*
       * Some basic queries. This assumes the following table exists within the JBDC demo database:
       * 
       * CREATE TABLE EMPLOYEE ( ID INT NOT NULL DEFAULT 0 , FIRST_NAME VARCHAR(100) NOT NULL ,
       * LAST_NAME VARCHAR(100) NULL );
       * 
       * The table does not have to be empty, but it is recommended to be, as all contents will be
       * deleted.
       */

      // read the current contents

      System.out.println("Contents of the table 'employee': ");
      Statement selectStatement = connection.createStatement();
      ResultSet rs = selectStatement.executeQuery("SELECT * FROM employee");
      while (rs.next()) {
        System.out.println(rs.getString(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
      }

      // add 3 entries

      System.out.println("Let's add some people.");
      Statement insertStatement = connection.createStatement();
      insertStatement
          .execute("INSERT INTO employee (ID,FIRST_NAME,LAST_NAME) VALUES " + "(1,'John','Smith')");
      insertStatement
          .execute("INSERT INTO employee (ID,FIRST_NAME,LAST_NAME) VALUES " + "(2,'Mary','Jane')");
      insertStatement.execute(
          "INSERT INTO employee (ID,FIRST_NAME,LAST_NAME) VALUES " + "(3,'Drake','Taylor')");

      // reread the contents

      System.out.println("'employee' Now looks like this: ");
      rs = selectStatement.executeQuery("SELECT * FROM employee");
      while (rs.next()) {
        System.out.println(rs.getString(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
      }

      // update 1 entry

      System.out.println("Wait, who's Mary? Let's replace that.");
      Statement updateStatement = connection.createStatement();
      updateStatement
          .execute("UPDATE employee SET FIRST_NAME = 'Bethany' " + "WHERE FIRST_NAME = 'MARY'");

      // one last read

      System.out.println("Let's take one more look: ");
      rs = selectStatement.executeQuery("SELECT * FROM employee");
      while (rs.next()) {
        System.out.println(rs.getString(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
      }

      // delete all entries

      System.out.println("You know what, we don't need these people. Off with their heads!");
      Statement deleteStatement = connection.createStatement();
      deleteStatement.execute("DELETE FROM employee WHERE ID >= 1");

      selectStatement.close();
      insertStatement.close();
      updateStatement.close();
      deleteStatement.close();

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
