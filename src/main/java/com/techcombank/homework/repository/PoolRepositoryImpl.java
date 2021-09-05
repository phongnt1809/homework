package com.techcombank.homework.repository;


import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.CalculatedQuantileResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import org.springframework.stereotype.Repository;


/**
 * Implement based on wiki definition
 * Ref: @link https://en.wikipedia.org/wiki/Quantile
 *
 * */
@Repository
public class PoolRepositoryImpl implements PoolRepository {

  /**
   * Hashtable control concurrent update on key
   * Entry:
   *  - key -> poolId
   *  - value -> pool's Sorted values
   * */
  private final Hashtable<Long, SortedArrayList> poolHashTable = new Hashtable<>();

  public ACTION addToPool(Long poolId, ArrayList<Long> values) {
    ACTION action = ACTION.INSERTED;
    boolean exist = poolHashTable.containsKey(poolId);
    //todo: implement WAL for resiliency

    if (exist) {
      action = ACTION.APPENDED;
    }
    /* set values on insert
     * append values if exist
     *
     * */
    poolHashTable.compute(poolId, (k, v) -> {
      if(v == null) {
        return new SortedArrayList(values);
      } else {
        v.insertAndSort(values);
        return v;
      }
    });
    return action;
  }


  /**
   * formula: i = ceil(n * (p / 100))
   * - i: index of cut point
   * - n: size of dataset
   * - p: percentiles
   * */
  @Override
  public CalculatedQuantileResult query(Long poolId, float percentile) {
    SortedArrayList values = poolHashTable.get(poolId);
    if (values != null) {
      int n = values.size();
      if (percentile == 0) {
        return new CalculatedQuantileResult(n, values.get(0));
      }
      boolean evenSized = n % 2 == 0;
      int i = (int) Math.ceil(n * (percentile / 100));
      if (evenSized && percentile == 50F) {
        return new CalculatedQuantileResult(n, (values.get(i-1) + values.get(i)) / 2 );
      } else {
        return new CalculatedQuantileResult(n, values.get(i-1));
      }
    }
    return null;
  }
}

/*
* asc order arraylist
* */
class SortedArrayList extends ArrayList<Long> {
  public SortedArrayList(Collection<? extends Long> c) {
    super(c);
    sort(Long::compare);
  }

  public void insertAndSort(ArrayList<Long> elements) {
    addAll(elements);
    sort(Long::compare);
  }
}
