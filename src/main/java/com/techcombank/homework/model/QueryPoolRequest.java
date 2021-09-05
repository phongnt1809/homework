package com.techcombank.homework.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class QueryPoolRequest {

  @NotNull
  @Positive
  public Long poolId;

  @NotNull
  @Min(0)
  @Max(100)
  public Float percentile;

  @Override
  public String toString() {
    return "QueryPoolRequest{" +
        "poolId=" + poolId +
        ", percentile=" + percentile +
        '}';
  }
}
