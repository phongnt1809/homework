package com.techcombank.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class AddValuesResponse {
  /**
   *
   * */
  private final ACTION action;

  @Override
  public String toString() {
    return "AddValuesResponse{" +
        "action=" + action +
        '}';
  }
}

