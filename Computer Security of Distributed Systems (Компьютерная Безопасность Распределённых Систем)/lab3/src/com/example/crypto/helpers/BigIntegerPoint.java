package com.example.crypto.helpers;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

public class BigIntegerPoint implements Serializable {
  @Serial
  private static final long serialVersionUID = 11L;
  private BigInteger x = new BigInteger("0");
  private BigInteger y = new BigInteger("0");

  public BigIntegerPoint() {
  }

  public BigIntegerPoint(int x, int y) {
    this.x = new BigInteger(String.valueOf(x));
    this.y = new BigInteger(String.valueOf(y));
  }

  public BigIntegerPoint(BigInteger x, BigInteger y) {
    this.x = x;
    this.y = y;
  }

  public BigIntegerPoint(IntegerPoint integerPoint) {
    this(integerPoint.getX(), integerPoint.getY());
  }

  public BigInteger getX() {
    return x;
  }

  public BigInteger getY() {
    return y;
  }

  public void setX(int x) {
    this.x = new BigInteger(String.valueOf(x));
  }

  public void setY(int y) {
    this.y = new BigInteger(String.valueOf(y));
  }

  public void setX(BigInteger x) {
    this.x = x;
  }

  public void setY(BigInteger y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + "; " + y + ")";
  }
}
