package com.techcombank.homework.repository;

import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.CalculatedQuantileResult;
import java.util.ArrayList;

public interface PoolRepository {
  ACTION addToPool(Long poolId, ArrayList<Long> values);
  CalculatedQuantileResult query(Long poolId, float percentile);
}
