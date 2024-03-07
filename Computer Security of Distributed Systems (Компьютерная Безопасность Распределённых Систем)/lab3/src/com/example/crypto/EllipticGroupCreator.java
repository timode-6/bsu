package com.example.crypto;

import com.example.crypto.helpers.IntegerPoint;

import java.util.ArrayList;
import java.util.List;

public class EllipticGroupCreator {
  public static List<IntegerPoint> findEllipticGroup(int M, int a, int b) {
    long aSqr = (long) a * a;
    long bSqr = (long) b * b;
    long tmp = (4 * (aSqr % M) + 27 * (bSqr % M)) % M;
    if (tmp == 0) {
      throw new IllegalArgumentException("Parameters don't satisfy the " +
              "relation 4*a^2 + 27*b^2 ≠ 0 (mod M)");
    }
    int[][] quadraticResidues = new int[M][];
    ArrayList<IntegerPoint> res = new ArrayList<>();
    res.add(new IntegerPoint(-1, -1));
    for (int y = 1; y <= (M - 1) / 2; ++y) {
      int residue = (int) (((long) y * y) % M);
      quadraticResidues[residue] = new int[]{y, M - y};
    }
    for (int x = 0; x < M; ++x) {
      long equationRightPart = ((((long) x * x) % M) * x) % M;
      equationRightPart += ((long) a * x) % M;
      equationRightPart = ((equationRightPart + b) % M + M) % M;
      if (equationRightPart == 0) {
        res.add(new IntegerPoint(x, 0));
      }
      int[] residues = quadraticResidues[(int) equationRightPart];
      if (residues != null) {
        res.add(new IntegerPoint(x, residues[0]));
        res.add(new IntegerPoint(x, residues[1]));
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<IntegerPoint> res = findEllipticGroup(47, 1, 4);
    for (IntegerPoint point : res) {
      System.out.println("(" + point.getX() + "; " + point.getY() + ")");
    }
    System.out.println("--------------------------");
    System.out.println(res.size());
  }
}
