package hack_isu_f16.util;

public class Utils
{
  private Utils()
  {
    /* No */
  }
  
  public static boolean equalsApprox(double a, double b, double maxDelta)
  {
    return Math.abs(a - b) <= maxDelta;
  }
}
