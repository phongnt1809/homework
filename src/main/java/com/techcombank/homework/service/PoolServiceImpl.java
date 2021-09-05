package com.techcombank.homework.service;

import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.CalculatedQuantileResult;
import com.techcombank.homework.repository.PoolRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoolServiceImpl implements PoolService{

  @Autowired
  PoolRepository repository;

  @Override
  public ACTION addToPool(Long poolId, ArrayList<Long> values) {
    /*
    * todo: do sth to enhance HA
    * ex: replicate to other instances
    * */
    return repository.addToPool(poolId, values);
  }

  @Override
  public CalculatedQuantileResult query(Long poolId, float percentile) {
    return repository.query(poolId, percentile);
  }
}
