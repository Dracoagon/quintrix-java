package com.quintrix.jfs;

public class LoginCredentials {

  private String username;
  private String password;

  LoginCredentials() {
    username = "";
    password = "";
  }

  LoginCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void printCredentials() {
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);
  }
}
