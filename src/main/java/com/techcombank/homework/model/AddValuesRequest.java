package com.techcombank.homework.model;

import java.util.ArrayList;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AddValuesRequest {

  @NotNull
  @Digits(fraction = 0, integer = 5)
  private Long poolId;

  @NotNull
  @Size(min = 1)
  private ArrayList<@Digits(fraction = 0, integer = 5) @NotNull Long> values;

  @Override
  public String toString() {
    return "AddValuesRequest{" +
        "poolId=" + poolId +
        ", values=" + values +
        '}';
  }
}
