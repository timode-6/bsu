package com.example.keyexchangeexample;

import com.example.crypto.KeysGenerator;
import com.example.crypto.PointOperationsManager;
import com.example.crypto.helpers.BigIntegerPoint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;

public class ClientB {
  private static final String HOST = "localhost";
  private static final int PORT = 50001;
  private static Socket socket;
  private static ObjectOutputStream oos;
  private static ObjectInputStream ois;

  private static void connect() throws IOException {
    socket = new Socket(HOST, PORT);
    ois = new ObjectInputStream(socket.getInputStream());
    oos = new ObjectOutputStream(socket.getOutputStream());
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
      KeysGenerator keysGenerator = new KeysGenerator(pom);
      BigIntegerPoint userAPublicKey = (BigIntegerPoint) ois.readObject();
      System.out.println("Got user A public key: " + userAPublicKey);
      BigIntegerPoint publicKey = keysGenerator.generatePublicKey(
              G, pointOrder);
      BigInteger secretKey = keysGenerator.getSecretKey();
      System.out.println("Secret key = " + secretKey);
      System.out.println("Public key = " + publicKey);
      System.out.println("Sending public key...");
      oos.writeObject(publicKey);
      oos.flush();
      System.out.println("Common key = " +
              keysGenerator.generateCommonSecretKey(userAPublicKey));
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
