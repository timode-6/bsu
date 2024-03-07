package com.example.crypto;

import com.example.crypto.helpers.BigIntegerPoint;

import java.math.BigInteger;

public class PointOperationsManager {
  private BigInteger M;
  private BigInteger a;
  private BigInteger b;

  public PointOperationsManager(BigInteger M, BigInteger a, BigInteger b) {
    this.M = M;
    this.a = a;
    this.b = b;
  }

  public BigInteger getM() {
    return M;
  }

  public void setM(BigInteger m) {
    M = m;
  }

  public BigInteger getA() {
    return a;
  }

  public void setA(BigInteger a) {
    this.a = a;
  }

  public BigInteger getB() {
    return b;
  }

  public void setB(BigInteger b) {
    this.b = b;
  }

  private static BigInteger[] extendedEuclideanAlgorithm(
          BigInteger a, BigInteger b) {
    BigInteger zero = new BigInteger("0"), one = new BigInteger("1");
    BigInteger s = zero, old_s = one, t = one, old_t = zero;
    BigInteger r = b, old_r = a;
    while (r.compareTo(zero) != 0) {
      BigInteger quotient = old_r.divide(r);
      BigInteger tmp = old_r.subtract(quotient.multiply(r));
      old_r = r;
      r = tmp;
      tmp = old_s.subtract(quotient.multiply(s));
      old_s = s;
      s = tmp;
      tmp = old_t.subtract(quotient.multiply(t));
      old_t = t;
      t = tmp;
    }
    return new BigInteger[]{old_r, old_s, old_t};
  }

  public BigInteger getInverseOf(BigInteger num) {
    return getInverseOf(num, M);
  }

  public static BigInteger getInverseOf(BigInteger num, BigInteger mod) {
    BigInteger[] euclidAlgoRes = extendedEuclideanAlgorithm(num, mod);
    if (euclidAlgoRes[0].compareTo(new BigInteger("1")) != 0) {
      return null;
    }
    return euclidAlgoRes[1].mod(mod);
  }

  public BigIntegerPoint add(BigIntegerPoint first, BigIntegerPoint second) {
    BigInteger minusOne = new BigInteger("-1");
    if (first.getX().compareTo(minusOne) == 0) {
      return second;
    }
    if (second.getX().compareTo(minusOne) == 0) {
      return first;
    }
    if (first.getX().compareTo(second.getX()) == 0 &&
            first.getY().compareTo(second.getY()) != 0) {
      return new BigIntegerPoint(-1, -1);
    }
    BigInteger m;
    BigInteger two = new BigInteger("2"), three = new BigInteger("3");
    if (first.getX().compareTo(second.getX()) == 0 &&
            first.getY().compareTo(second.getY()) == 0) {
      m = first.getX().multiply(first.getX()).multiply(three).add(a);
      BigInteger inversedVal = getInverseOf(two.multiply(first.getY()));
      if (inversedVal == null) {
        return new BigIntegerPoint(-1, -1);
      }
      m = m.multiply(inversedVal).mod(M);
    } else {
      BigInteger inversedVal = getInverseOf(
              first.getX().subtract(second.getX()));
      if (inversedVal == null) {
        BigIntegerPoint tmp = first;
        first = second;
        second = tmp;
        inversedVal = getInverseOf(first.getX().subtract(second.getX()));
        if (inversedVal == null) {
          return new BigIntegerPoint(-1, -1);
        }
      }
      m = first.getY().subtract(second.getY());
      m = m.multiply(inversedVal).mod(M);
    }
    BigInteger xRes = m.multiply(m).subtract(
            first.getX()).subtract(second.getX()).mod(M);
    BigInteger yRes = m.multiply(
            first.getX().subtract(xRes)).subtract(first.getY()).mod(M);
    return new BigIntegerPoint(xRes, yRes);
  }

  public BigIntegerPoint multiply(BigInteger n, BigIntegerPoint point) {
    BigInteger zero = new BigInteger("0");
    BigIntegerPoint currPoint = point;
    BigIntegerPoint res = new BigIntegerPoint(-1, -1);
    while (n.compareTo(zero) != 0) {
      if (n.testBit(0)) {
        res = add(res, currPoint);
      }
      currPoint = add(currPoint, currPoint);
      n = n.shiftRight(1);
    }
    return res;
  }

  public static boolean equals(BigIntegerPoint first, BigIntegerPoint second) {
    return first.getX().compareTo(second.getX()) == 0 &&
            first.getY().compareTo(second.getY()) == 0;
  }
}
