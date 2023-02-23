package squareMultiply;

public class squareMultiply
{
  public int modExpoCalculator(int base, int exponent, int modulo)
  {
    int a = 1;
    while (exponent > 0) {
      if ((exponent % 2) == 1) {
        a = (a * base) % modulo;
      }
      base = (base * base) % modulo;
      exponent = (int)Math.floor((double)(exponent / 2));
    }
    return a;
  }
}
