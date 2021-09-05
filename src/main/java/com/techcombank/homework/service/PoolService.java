package com.techcombank.homework.service;

import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.CalculatedQuantileResult;
import java.util.ArrayList;

public interface PoolService {
  ACTION addToPool(Long poolId, ArrayList<Long> values);
  CalculatedQuantileResult query(Long poolId, float percentile);
}
