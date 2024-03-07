package com.example.ecdsaexample;

import com.example.crypto.DigitalSignatureManager;
import com.example.crypto.PointOperationsManager;
import com.example.crypto.helpers.BigIntegerPoint;
import com.example.helpers.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.Socket;

public class ClientB {
  private static final String HOST = "localhost";
  private static final int PORT = 50002;
  private static Socket socket;
  private static ObjectInputStream ois;

  private static void connect() throws IOException {
    socket = new Socket(HOST, PORT);
    ois = new ObjectInputStream(socket.getInputStream());
  }

  public static void main(String[] args) {
    try {
      connect();
      BigInteger M = (BigInteger) ois.readObject(),
              a = (BigInteger) ois.readObject(),
              b = (BigInteger) ois.readObject(),
              curveOrder = (BigInteger) ois.readObject(),
              pointOrder = (BigInteger) ois.readObject();
      BigIntegerPoint G = (BigIntegerPoint) ois.readObject();
      PointOperationsManager pom = new PointOperationsManager(M, a, b);
      System.out.println("Got elliptic group parameters:");
      System.out.println("M = " + M + "; a = " + a + "; b = " + b +
              "; G = " + G + "; curve order = " + curveOrder +
              "; point order = " + pointOrder);
      BigIntegerPoint userAPublicKey = (BigIntegerPoint) ois.readObject();
      System.out.println("Got user A public key: " + userAPublicKey);
      var signature =
              (Pair<BigInteger, BigInteger>) ois.readObject();
      System.out.println("Got signature:");
      System.out.println("r = " + signature.getFirst() + "; s = " +
              signature.getSecond());
      String msg = ois.readUTF();
      DigitalSignatureManager dsm = new DigitalSignatureManager(
              userAPublicKey, G, pointOrder, pom);
      boolean checkSuccessful = dsm.checkSignature(signature, msg);
      if (checkSuccessful) {
        System.out.println("Signature check is successful");
      } else {
        System.out.println("Unknown signature");
      }
      System.out.println("Message: " + msg);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (socket != null) {
          socket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
