package com.techcombank.homework.exception;

public class InvalidPoolId extends Exception{

  public InvalidPoolId() {
    super("Pool id is not exist ");
  }

  public InvalidPoolId(String message) {
    super(message);
  }
}
