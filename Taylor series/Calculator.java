import java.util.Scanner; 

public class SinCalculator { 

  public final static String DEGREES_MODE = "D"; 
  public final static String RADIANS_MODE = "R"; 

  public static void main(String[] args) { 
    String mode = processMode(); 
    double x = processX(mode); 
    int termCount = processTermCount(); 
    double result = calcSin(x, termCount); 

  System.out.println("sin(x) = " + result); 
} 

  public static String processMode() { 
    String mode; 
    Scanner scanner = new Scanner(System.in); 

    while (true) { 
      System.out.println("If x is measured in:\nDegrees: enter D\nRadians: enter R"); 
      mode = scanner.next(); 
        if (!mode.equals(DEGREES_MODE) && !mode.equals(RADIANS_MODE)) { 
        System.out.println("Illegal mode"); 
      continue; 
    } else { 
      return mode; 
} 
} 
} 

  public static double processX(String mode) { 
      double x; 
  Scanner scanner = new Scanner(System.in); 

  while (true) { 
    System.out.println("Enter the value of x"); 
    String strX; 
      try { 
        strX = scanner.next(); 
        if (mode.equals(RADIANS_MODE) && Character.toString(strX.charAt(strX.length() - 1)).equals("p")) { 
        if (strX.length() == 1) { 
        x = Math.PI; 
      } else { 
        x = Double.parseDouble(strX.substring(0, strX.length() - 1)) * Math.PI; 
} 
      } else if (mode.equals(RADIANS_MODE)) { 
        x = Double.parseDouble(strX); 
      } else { 
        x = Math.toRadians(Double.parseDouble(strX)); 
} 
      return x; 
} catch (NumberFormatException e) { 
System.out.println("Illegal x, enter again:"); 
} 
} 
} 

  public static int processTermCount() { 
    int k; 
    Scanner scanner = new Scanner(System.in); 

      while (true) { 
      System.out.println("Enter number of terms"); 
      try { 
      k = scanner.nextInt(); 
      if(k <= 0) { 
      System.out.println("This must be a natural number"); 
      continue; 
} 
      return k; 
} catch (NumberFormatException e) { 
System.out.println("The entered value must be an integer"); 
} 
} 
} 

  public static double calcSin(double x, int termCount) { 
    double result = 0; 

      if (x > Math.PI * 2) { 
      x %= (Math.PI * 2); 
} 
      if (x > Math.PI / 2) { 
      x = Math.PI - x; 
} 
      if (x < 0) { 
      return -calcSin(-x, termCount); 
} 

    long factorial = 1; // current factorial value 
    double tempX = x; // x to the current power 
    int t = 1; 
      while(t <= termCount) { 
        if(t % 2 == 0) { 
      result -= tempX / factorial; 
        } else { 
      result += tempX / factorial; 
} 

    factorial *= t * 2 * (t * 2 + 1); 
    tempX = tempX * x * x; 
    t++; 
} 
  return result; 
} 

}
