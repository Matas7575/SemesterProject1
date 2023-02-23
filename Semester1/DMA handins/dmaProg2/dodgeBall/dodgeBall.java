package dodgeBall;

import java.util.TreeSet;

public class dodgeBall
{
  private TreeSet<Integer> team = new TreeSet<>();
  public void addPlayer(int x) {
    team.add(x);
  }
  public int throwBall(int x) {
    int distance = 0;
    if(team.contains(x))
    team.remove(x);
    else {
      int[] array = new int[team.size()];
      int i=0, aux=0;
      for (int ele : team) {
          array[i++]=ele;
      }
      distance = array[array.length-1];
      for(int j=0;j<array.length;j++)
      {
        int dif = array[j]-x;
        if(dif<0)
          dif*=-1;
        if(dif<distance) {
          if(aux!=0) {
            array[j-1]=aux;
          }
          aux=array[j];
          array[j] = x;
          distance = dif;
        }
      }
      team.remove(aux);
      team.add(x);
    }
    return distance;
    }
  }
