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

  public static class RepeatingRunnable implements Runnable
  {
    private Runnable toRun;
    private volatile boolean running;
    private final long millis;
    private final int nanos;

    public RepeatingRunnable(Runnable toRun, long millis)
    {
      this(toRun, millis, 0);
    }
    
    public RepeatingRunnable(Runnable toRun, long millis, int nanos)
    {
      this.toRun = toRun;
      this.millis = millis;
      this.nanos = nanos;
    }

    public void run()
    {
      running = true;
      long t;
      long m;
      int n;

      while (running)
      {
        t = System.nanoTime();
        toRun.run();
        t = System.nanoTime() - t;

        m = millis - t / 1000000;
        n = nanos - (int) (t % 1000000);

        while (n < 0)
        {
          --m;
          n += 1000000;
        }

        try
        {
          Thread.sleep(m, n);
        }
        catch (InterruptedException e)
        {

        }
      }
    }

    public void stop()
    {
      running = false;
    }
  }
}
