package com.techcombank.homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculatedQuantileResult {
  long count;
  long result;

  @Override
  public String toString() {
    return "CalculatedQuantileResult{" +
        "count=" + count +
        ", result=" + result +
        '}';
  }
}
