package com.quintrix.jfs;

public class LuckyNumbers {

  /*
   * A class to hold a name and a number, for use with Lottery
   */

  private int luckyNumber;
  private String name;

  LuckyNumbers(int x, String s) {
    this.luckyNumber = x;
    this.name = s;
  }

  public String getName() {
    return name;
  }

  public int getNum() {
    return luckyNumber;
  }

  public boolean checkNum(int num) {
    if (num == luckyNumber)
      return true;
    else
      return false;
  }
}
