package extendedEA;

import java.util.Arrays;
import java.util.Scanner;

public class extendedEA
{
  public static int[] EAA(int a, int b) {
    int x = 0, y = 1, lastx = 1, lasty = 0, aux;
    while (b != 0)
    {
      int q = a / b, r = a % b;
      a = b;
      b = r;

      aux = x;
      x = lastx - q * x;
      lastx = aux;

      aux = y;
      y = lasty - q * y;
      lasty = aux;
    }
      return new int[]{lastx,lasty};
  }



  // Do not change methods below;

  private static int mod(int a, int b) {
    return ((a%b)+b) % b;
  }

  public static int moduloInverse(int a, int b) {
    int[] result = EAA(a, b);
    return mod(result[0], b);
  }
  public static void main(String[] args)
  {
    // input
    System.out.println("Extended Euclidian Algorithm");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Expression calculator for gcd(a, b)");
    System.out.print("Give value for a: ");
    int a = scanner.nextInt();
    System.out.print("Give value for b: ");
    int b = scanner.nextInt();
    System.out.println();
    System.out.println(Arrays.toString(EAA(a,b)));
    System.out.println(GCD.findGCD(a,b) + " = " + a + "(" + EAA(a,b)[0] + ")"
        + ((b < 0) ? " - " : " + ")
        + Math.abs(b) + "(" + EAA(a,b)[1] + ")");
    System.out.println((GCD.findGCD(a,b) != 1 ? "No inverse exists" : "The inverse of "
        + a + " mod " + b + " is " + EAA(a,b)[0]));

  }
}
