package com.example.keyexchangeexample;

import com.example.crypto.EllipticGroupCreator;
import com.example.crypto.KeysGenerator;
import com.example.crypto.PointOperationsManager;
import com.example.crypto.helpers.BigIntegerPoint;
import com.example.crypto.helpers.IntegerPoint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientA {
  private static final int PORT = 50001;
  private static ServerSocket serverSocket;
  private static ObjectOutputStream oos;
  private static ObjectInputStream ois;
  private static final Scanner scanner = new Scanner(System.in);
  private static final BigInteger M = new BigInteger("47");
  private static final BigInteger A = new BigInteger("1");
  private static final BigInteger B = new BigInteger("4");

  private static void connect() throws IOException {
    serverSocket = new ServerSocket(PORT);
    Socket socket = serverSocket.accept();
    oos = new ObjectOutputStream(socket.getOutputStream());
    ois = new ObjectInputStream(socket.getInputStream());
  }

  private static List<BigIntegerPoint> getPoints() {
    List<IntegerPoint> ellipticGroup = EllipticGroupCreator.findEllipticGroup(
            M.intValue(), A.intValue(), B.intValue());
    List<BigIntegerPoint> ellipticGroupBigInt = new ArrayList<>(
            ellipticGroup.size());
    for (IntegerPoint point : ellipticGroup) {
      ellipticGroupBigInt.add(new BigIntegerPoint(point));
    }
    return ellipticGroupBigInt;
  }

  public static void main(String[] args) {
    try {
      connect();
      PointOperationsManager pom = new PointOperationsManager(M, A, B);
      System.out.println("M = " + M + "; a = " + A + "; b = " + B);
      KeysGenerator keysGenerator = new KeysGenerator(pom);
      var pointsList = getPoints();
      BigInteger curveOrder = new BigInteger(
              String.valueOf(pointsList.size()));
      System.out.println("Curve order = " + curveOrder +
              "\nEnter point order: ");
      BigInteger pointOrder = scanner.nextBigInteger();
      BigIntegerPoint G = keysGenerator.findG(
              curveOrder, pointOrder, pointsList);
      System.out.println("G = " + G);
      System.out.println("Sending elliptic group parameters... ");
      oos.writeObject(M);
      oos.writeObject(A);
      oos.writeObject(B);
      oos.writeObject(curveOrder);
      oos.writeObject(pointOrder);
      oos.writeObject(G);
      BigIntegerPoint publicKey = keysGenerator.generatePublicKey(
              G, pointOrder);
      BigInteger secretKey = keysGenerator.getSecretKey();
      System.out.println("Secret key = " + secretKey);
      System.out.println("Public key = " + publicKey);
      System.out.println("Sending public key...");
      oos.writeObject(publicKey);
      oos.flush();
      BigIntegerPoint userBPublicKey = (BigIntegerPoint) ois.readObject();
      System.out.println("Got user B public key: " + userBPublicKey);
      System.out.println("Common key = " +
              keysGenerator.generateCommonSecretKey(userBPublicKey));
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (serverSocket != null) {
          serverSocket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
