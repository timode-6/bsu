package com.example.helpers;

import java.io.Serial;
import java.io.Serializable;

public class Pair<K, V> implements Serializable {
  @Serial
  private static final long serialVersionUID = 22L;
  private K first;
  private V second;

  public Pair(K first, V second) {
    this.first = first;
    this.second = second;
  }

  public K getFirst() {
    return first;
  }

  public void setFirst(K first) {
    this.first = first;
  }

  public V getSecond() {
    return second;
  }

  public void setSecond(V second) {
    this.second = second;
  }
}
