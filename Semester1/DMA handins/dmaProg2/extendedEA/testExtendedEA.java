package extendedEA;

import java.util.Arrays;
import java.util.ArrayList;

public class testExtendedEA
{
  public static void main(String[] args)
  {
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    test7();
    test8();
  }

  private static void test1()
  {
    int[] numberList = {0, 1, 2, 3, 4, 5};
    int b = 78;

    for (int a : numberList)
    {
      int resultGCD = GCD.findGCD(a, b);

      System.out.println("Greatest Common Divisor: " + resultGCD);

      if (resultGCD == 1)
      {
        int inverse = extendedEA.moduloInverse(a, b);
        System.out.println(
            "Inverse of " + String.valueOf(a) + " mod " + String.valueOf(b)
                + " is: " + String.valueOf(inverse));
      }
      else
      {
        System.out.println(
            "Inverse of " + String.valueOf(a) + " mod " + String.valueOf(b)
                + " is: No Inverse");
      }
      System.out.println("[Pass Test1]");
      System.out.println("------------------------------");

    }
  }

  private static void test2()
  {
    int a = 4;
    int b = 78;

    int[] gcd = {-19, 1};

    runTest(a, b, gcd, "test2");

  }

  private static void test3()
  {
    int a = 34;
    int b = 294;

    int[] gcd = {26, -3};

    runTest(a, b, gcd, "test3");

  }

  private static void test4()
  {
    int a = 294;
    int b = 34;

    int[] gcd = {-3, 26};

    runTest(a, b, gcd, "test4");

  }

  private static void test5()
  {
    int a = -294;
    int b = 34;

    int[] gcd = {3, 26};

    runTest(a, b, gcd, "test5");

  }

  private static void test6()
  {
    int a = 0;
    int b = 34;

    int[] gcd = {0, 1};

    runTest(a, b, gcd, "test6");

  }

  private static void test7()
  {
    int a = 3;
    int b = 244;

    int[] gcd = {-81, 1};

    runTest(a, b, gcd, "test7");

  }

  ;

  private static void runTest(int a, int b, int[] gcdResult, String testName)
  {
    extendedEA gcdSolver = new extendedEA();
    int[] gcd;

    try
    {
      gcd = gcdSolver.EAA(a, b);
    }
    catch (Exception e)
    {
      outputFail(testName, "Failed with exception: " + e);
      return;
    }

    if (gcd[0] != gcdResult[0] || gcd[1] != gcdResult[1])
    {
      outputFail(testName,
          "Got: " + Arrays.toString(gcd) + " but expected: " + Arrays.toString(
              gcdResult));
    }
    else
    {
      outputPass(testName);
    }
  }

  private static void outputPass(String testName)
  {
    System.out.println("[Pass " + testName + "]");

  }

  private static void outputFail(String testName, String message)
  {
    System.out.println("[FAIL " + testName + "] " + message);
  }

  private static void test8()
  {
    int[] inputa = {95, 66, 82};
    int[] inputb = {37, 100, 96};
    int[] correctAnswers = {-7, -1, -1, -14, -3, 3, 14, 11, -7};
    int val = 0;

    ArrayList<Integer> correct = new ArrayList<Integer>();

    for (int k : inputa)
      for (int i : inputb)
        correct.add(extendedEA.EAA(k, i)[0]);

    for (int i = 0; i < correctAnswers.length; i++)
    {
      if (correct.get(i) != correctAnswers[i])
        outputFail("test8",
            "Expected output " + correctAnswers[i] + " but got " + correct.get(
                i));
      else
        val++;

    }
    if (val == correctAnswers.length)
      outputPass("test8");
  }
}


