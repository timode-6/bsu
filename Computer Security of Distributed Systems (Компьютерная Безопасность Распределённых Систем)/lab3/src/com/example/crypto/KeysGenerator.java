package com.example.crypto;

import com.example.crypto.helpers.BigIntegerPoint;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class KeysGenerator {
  private PointOperationsManager pom;
  private final SecureRandom randomGen = new SecureRandom();
  private BigInteger secretKey;

  public KeysGenerator(BigInteger M, BigInteger a, BigInteger b) {
    pom = new PointOperationsManager(M, a, b);
  }

  public KeysGenerator(PointOperationsManager pom) {
    this.pom = pom;
  }

  public PointOperationsManager getPointOperarionsManager() {
    return pom;
  }

  public void setPointOperationsManager(PointOperationsManager pom) {
    this.pom = pom;
  }

  public BigInteger getSecretKey() {
    return secretKey;
  }

  public BigIntegerPoint findG(BigInteger curveOrder, BigInteger pointOrder,
                               List<BigIntegerPoint> pointsArr) {
    BigInteger h = curveOrder.divide(pointOrder);
    BigIntegerPoint O = new BigIntegerPoint(-1, -1);
    BigIntegerPoint res;
    for (BigIntegerPoint point : pointsArr) {
      res = pom.multiply(h, point);
      if (!PointOperationsManager.equals(res, O)) {
        return res;
      }
    }
    return null;
  }

  public BigIntegerPoint generatePublicKey(BigIntegerPoint G,
                                           BigInteger pointOrder) {
    BigInteger one = new BigInteger("1");
    BigInteger tmp = pointOrder.subtract(one);
    do {
      secretKey = new BigInteger(tmp.bitLength(), randomGen);
    } while (secretKey.compareTo(tmp) >= 0);
    secretKey = secretKey.add(one);
    return pom.multiply(secretKey, G);
  }

  public BigIntegerPoint generateCommonSecretKey(
          BigIntegerPoint otherUserPublicKey) {
    return pom.multiply(secretKey, otherUserPublicKey);
  }
}
