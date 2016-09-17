package hack_isu_f16.geom;

/**
 * A class representing an immutable two-dimensional vector.
 * 
 * @author Mike
 */
public class Vector2D
{
  /**
   * The x component of this vector.
   */
  final double x;

  /**
   * The y component of this vector.
   */
  final double y;

  /**
   * The unit vector in the x direction, i.
   */
  public static final Vector2D I = new Vector2D(1, 0);

  /**
   * The unit vector in the y direction, j.
   */
  public static final Vector2D J = new Vector2D(0, 1);

  /**
   * Constructs a vector with the given x and y components.
   * 
   * @param x
   *          the x component of the vector
   * @param y
   *          the y component of the vector
   */
  public Vector2D(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x component of this vector.
   * 
   * @return the x component of this vector
   */
  public double getX()
  {
    return x;
  }

  /**
   * Returns the y component of this vector.
   * 
   * @return the y component of this vector
   */
  public double getY()
  {
    return y;
  }

  /**
   * Returns the magnitude of this vector.
   * 
   * @return the magnitude of this vector
   * @see #getMagnitudeSquared()
   */
  public double getMagnitude()
  {
    return Math.sqrt(getMagnitudeSquared());
  }

  /**
   * Returns the magnitude of this vector, squared.
   * 
   * @return the magnitude of this vector, squared
   * @see #getMagnitude()
   */
  public double getMagnitudeSquared()
  {
    return this.dotProduct(this);
  }

  /**
   * Returns the angle between this vector and the x-axis, in radians, measured
   * counterclockwise.
   * 
   * @return the angle between this vector and the x-axis, in radians, measured
   *         counterclockwise
   */
  public double getTheta()
  {
    return y >= 0 ? this.angleBetween(I) : 2 * Math.PI - this.angleBetween(I);
  }

  /**
   * Returns the angle between this vector and the given vector, in radians, in
   * the range [0, &#x03C0;].
   * 
   * @param other
   *          the vector to determine the angle between
   * @return the angle between this vector and the given vector, in radians, in
   *         the range [0, &#x03C0;].
   */
  public double angleBetween(Vector2D other)
  {
    return Math.acos(this.dotProduct(other) / Math.sqrt(this.getMagnitudeSquared() * other.getMagnitudeSquared()));
  }

  /**
   * Returns the dot product of this vector and the given vector.<br>
   * The dot product of two vectors <b>u</b> and <b>v</b> is defined as<br>
   * <b>u</b> &#x2219; <b>v</b> = <b>u</b><sub>x</sub> * <b>v</b><sub>x</sub> +
   * <b>u</b><sub>y</sub> * <b>v</b><sub>y</sub> = |<b>u</b>| * |<b>v</b>| *
   * cos&#x03B8;<br>
   * where &#x03B8; is the smallest angle between the two vectors.
   * 
   * @param other
   *          the other operand for the dot product
   * @return the result of the dot product
   */
  public double dotProduct(Vector2D other)
  {
    return this.x * other.x + this.y * other.y;
  }

  /**
   * Returns the cross product of this vector and the given vector.<br>
   * The cross product of two (two-dimensional) vectors <b>u</b> and <b>v</b> is
   * defined as<br>
   * <b>u</b> &#x00D7; <b>v</b> = <b>u</b><sub>x</sub> * <b>v</b><sub>y</sub> -
   * <b>u</b><sub>y</sub> * <b>v</b><sub>x</sub> = |<b>u</b>| * |<b>v</b>| *
   * sin&#x03B8;<br>
   * where &#x03B8; is the smallest angle between the two vectors.
   * 
   * @param other
   *          the other operand for the cross product
   * @return the result of the cross product
   */
  public double crossProduct(Vector2D other)
  {
    return this.x * other.y - this.y * other.x;
  }

  /**
   * Returns true if the difference between this vector's and the given vector's
   * x and y components are each less than or equal to {@code maxDelta}.
   * 
   * @param other
   *          the vector to compare to this one
   * @param maxDelta
   *          the maximum tolerable difference for each component of the vectors
   * @return true if the difference between this vector's and the given vector's
   *         x and y components are less than or equal to {@code maxDelta}
   */
  public boolean equalsApprox(Vector2D other, double maxDelta)
  {
    return Math.abs(this.x - other.x) <= maxDelta && Math.abs(this.y - other.y) <= maxDelta;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }

    return equalsApprox((Vector2D) obj, 0.0);
  }

  @Override
  public int hashCode()
  {
    return Double.hashCode(x) ^ Double.hashCode(y);
  }

  @Override
  public String toString()
  {
    return "<" + x + ", " + y + ">";
  }
}
