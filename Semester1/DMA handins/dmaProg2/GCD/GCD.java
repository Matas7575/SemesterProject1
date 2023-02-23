package GCD;

public class GCD
{
  // Euclidean algorithm is the most simple to implement

  public int findGCD1(int a, int b)
  {
    int aux = 1;
    for(int i = 1; i <= a && i <= b; i++)
    {
      if(a%i==0 && b%i==0)
        aux = i;
    }
    return aux;
  }

  public int findGCD2(int a, int b)
  {
    while (a != 0 && b!=0)
    {
      int c = b;
      b = a % b;
      a = c;
    }
    return a+b;
  }

  public int findGCD3(int a, int b)
  {
    if(a==0)
      return b;
    return findGCD3(b % a, a);
  }
}
