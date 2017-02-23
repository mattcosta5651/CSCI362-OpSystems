import java.io.*;
import java.util.*;

public class Master
{
  private char[] alphabet = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
  public static void main(String args[]) throws Exception
  {
    new Master().go(args);
  }

  private void go(String[] args) throws Exception
  {
    List<Process> list = new ArrayList<Process>();
    for (char c : alphabet)
    {
      String letter = c+"";
      String word = args[0];
      System.out.printf("Starting process for %s...%n", letter);
      Process p = new ProcessBuilder("java", "Worker", letter+"", word+"").start();
      list.add(p);
    }
  
    for(Process p : list){
      p.waitFor();
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
      String line = br.readLine();
      while (line != null)
      {
        System.out.println(line);
        line = br.readLine();
      }    
    }
    
  }

}
