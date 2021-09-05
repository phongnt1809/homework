package com.techcombank.homework.model;

import java.util.ArrayList;
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
  private Long poolId;

  @NotNull
  @Size(min = 1)
  private ArrayList<Long> values;

  @Override
  public String toString() {
    return "AddValuesRequest{" +
        "poolId=" + poolId +
        ", values=" + values +
        '}';
  }
}
