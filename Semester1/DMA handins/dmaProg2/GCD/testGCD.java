package GCD;

public class testGCD
{
  private interface GCDFunction {
    int findGCD(int a, int b);
  }

  public static void main(String[] args) {
    GCDFunction gcd1 = (a, b) -> new GCD().findGCD1(a, b);
    GCDFunction gcd2 = (a, b) -> new GCD().findGCD2(a, b);
    GCDFunction gcd3 = (a, b) -> new GCD().findGCD3(a, b);

    GCDFunction[] methods = {gcd1, gcd2, gcd3};
    int index = 1;

    for (GCDFunction method : methods) {
      System.out.println("Test for findGCD"+index+":");
      test1(method);
      test2(method);
      test3(method);
      System.out.println("");
      index++;
    }

  }

  private static void test1(GCDFunction method) {
    int b = 13;
    int a = 5;

    int gcd = 1;

    runTest(a, b, gcd, "test1", method);
  }

  private static void test2(GCDFunction method) {
    int b = 30;
    int a = 10;

    int gcd = 10;

    runTest(a, b, gcd, "test2", method);
  }

  private static void test3(GCDFunction method) {
    int b = 28;
    int a = 16;

    int gcd = 4;

    runTest(a, b, gcd, "test3", method);
  }

  private static void runTest(int a, int b, int gcdResult, String testName, GCDFunction gcdSolver) {
    int gcd;

    try {
      gcd = gcdSolver.findGCD(a, b);
    } catch (Exception e) {
      outputFail(testName, "Failed with exception: " + e);
      return;
    }

    if (gcd != gcdResult) {
      outputFail(testName,
          "Got: " + gcd + " but expected: " + gcdResult);
    } else {
      outputPass(testName, "GCD(" + a + ", " + b + ")  = " + gcd);
    }
  }

  private static void outputPass(String testName, String message) {
    System.out.println("[Pass " + testName + "]" + message);

  }

  private static void outputFail(String testName, String message) {
    System.out.println("[FAIL " + testName + "] " + message);
  }
}
