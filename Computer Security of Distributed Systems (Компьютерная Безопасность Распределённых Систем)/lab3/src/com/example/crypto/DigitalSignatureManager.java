package com.example.crypto;

import com.example.crypto.helpers.BigIntegerPoint;
import com.example.helpers.Pair;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DigitalSignatureManager {
  private PointOperationsManager pom;
  private final SecureRandom randomGen = new SecureRandom();
  private BigIntegerPoint publicKey;
  private BigIntegerPoint G;
  private BigInteger pointOrder;
  private MessageDigest md;
  private Charset msgCharset = StandardCharsets.UTF_8;

  {
    try {
      md = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException ignored) {
    }
    md.reset();
  }

  public DigitalSignatureManager(BigIntegerPoint publicKey, BigIntegerPoint G,
                                 BigInteger pointOrder,
                                 PointOperationsManager pom) {
    this.publicKey = publicKey;
    this.G = G;
    this.pointOrder = pointOrder;
    this.pom = pom;
  }

  public void changeConfig(BigIntegerPoint publicKey,
                           BigIntegerPoint G, BigInteger pointOrder,
                           PointOperationsManager pom) {
    this.publicKey = publicKey;
    this.G = G;
    this.pointOrder = pointOrder;
    this.pom = pom;
  }

  public Charset getMsgCharset() {
    return msgCharset;
  }

  public void setMsgCharset(Charset msgCharset) {
    this.msgCharset = msgCharset;
  }

  public void changeAlgorithmForHash(String algoName)
          throws NoSuchAlgorithmException {
    md = MessageDigest.getInstance(algoName);
    md.reset();
  }

  private BigInteger getHash(String msg) {
    byte[] hash = md.digest(msg.getBytes(msgCharset));
    byte[] positiveHash = new byte[hash.length + 1];
    System.arraycopy(hash, 0, positiveHash, 1, hash.length);
    BigInteger res = new BigInteger(positiveHash);
    String strWithOnes = "1".repeat(pointOrder.bitLength());
    return res.and(new BigInteger(strWithOnes, 2));
  }

  public Pair<BigInteger, BigInteger> makeSignature(String msg,
                                                    BigInteger secretKey) {
    BigInteger hash = getHash(msg);
    BigInteger k, r, s;
    BigInteger zero = new BigInteger("0"), one = new BigInteger("1");
    BigInteger tmp = pointOrder.subtract(one);
    while (true) {
      do {
        k = new BigInteger(tmp.bitLength(), randomGen);
      } while (k.compareTo(tmp) >= 0);
      k = k.add(one);
      BigIntegerPoint intermediatePoint = pom.multiply(k, G);
      r = intermediatePoint.getX().mod(pointOrder);
      if (r.compareTo(zero) == 0) {
        continue;
      }
      s = PointOperationsManager.getInverseOf(k, pointOrder).multiply(hash.add(
              secretKey.multiply(r))).mod(pointOrder);
      if (s.compareTo(zero) == 0) {
        continue;
      }
      break;
    }
    return new Pair<>(r, s);
  }

  private boolean checkSignatureCoordinateRange(BigInteger coordinate) {
    return coordinate.compareTo(new BigInteger("0")) > 0 &&
            coordinate.compareTo(pointOrder) < 0;
  }

  public boolean checkSignature(Pair<BigInteger, BigInteger> signature,
                                String msg) {
    BigInteger hash = getHash(msg);
    if (!checkSignatureCoordinateRange(signature.getFirst()) ||
            !checkSignatureCoordinateRange(signature.getSecond())) {
      return false;
    }
    BigInteger w = PointOperationsManager.getInverseOf(
            signature.getSecond(), pointOrder).mod(pointOrder);
    BigInteger u1 = hash.multiply(w).mod(pointOrder);
    BigInteger u2 = signature.getFirst().multiply(w).mod(pointOrder);
    BigIntegerPoint intermediatePoint = pom.add(pom.multiply(u1, G),
            pom.multiply(u2, publicKey));
    BigInteger rStar = intermediatePoint.getX().mod(pointOrder);
    return rStar.compareTo(signature.getFirst()) == 0;
  }
}
