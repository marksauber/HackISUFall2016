package hack_isu_f16.util;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

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

  public static class ArrayUtils
  {
    public static Integer[] asWrapperArray(int... arr)
    {
      Integer[] toRet = new Integer[arr.length];
      for (int i = 0; i < arr.length; ++i)
      {
        toRet[i] = arr[i];
      }
      return toRet;
    }
    
    public static int[] asPrimitiveArray(Integer... arr)
    {
      int[] toRet = new int[arr.length];
      for (int i = 0; i < arr.length; ++i)
      {
        toRet[i] = arr[i] == null ? 0 : arr[i];
      }
      return toRet;
    }
    
    @SafeVarargs
    public static <T> T[] asArray(T... values)
    {
      return Arrays.copyOf(values, values.length);
    }
    
    public static <T> int indexOf(Object arr, Predicate<? super T> pred)
    {
      int l = Array.getLength(arr);
      for (int i = 0; i < l; ++i)
      {
        if (pred.test((T) Array.get(arr, i)))
        {
          return i;
        }
      }

      return -1;
    }

    public static int sum(int... nums)
    {
      int sum = 0;
      for (int i = 0; i < nums.length; ++i)
      {
        sum += nums[i];
      }

      return sum;
    }
  }

  public static <T> T weightedRandom(Random r, int[] weights, T[] choices)
  {
    if (weights.length > choices.length)
    {
      throw new IllegalArgumentException("Length mismatch");
    }
    if (ArrayUtils.indexOf(weights, (Integer t) -> t < 0) != -1)
    {
      throw new IllegalArgumentException("Negative weight");
    }
    int c = r.nextInt(ArrayUtils.sum(weights));
    int s = 0;
    for (int i = 0; i < weights.length; ++i)
    {
      s += weights[i];
      if (c < s)
      {
        return choices[i];
      }
    }

    return null;
  }

  public static BufferedImage[] splitImage(BufferedImage img, int rows, int cols)
  {
    BufferedImage[] toRet = new BufferedImage[rows * cols];
    int w = img.getWidth() / cols;
    int h = img.getHeight() / rows;

    for (int r = 0; r < rows; ++r)
    {
      for (int c = 0; c < cols; ++c)
      {
        BufferedImage im = new BufferedImage(w, h, img.getType());
        im.getGraphics().drawImage(img, c * -w, r * -h, null);
        toRet[r * rows + c] = im;
      }
    }

    return toRet;
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
