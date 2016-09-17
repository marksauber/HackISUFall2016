package hack_isu_f16.geom;

import hack_isu_f16.util.Utils;

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
   * Returns the unit vector in the same direction as this vector.
   * 
   * @return the unit vector in the same direction as this vector
   */
  public Vector2D normalize()
  {
    return this.multiply(1 / this.getMagnitude());
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
   * Returns a vector in the same direction as the given one with magnitude
   * equal to the magnitude of this vector in the direction of the given vector.
   * <br>
   * The projection of a vector <b>u</b> onto <b>v</b> is defined as<br>
   * <b>u</b> proj <b>v</b> = |<b>u</b>| * cos&#x03B8; * <b>v</b> / |<b>v</b>| =
   * (<b>u</b> &#x2219; <b>v</b>) * <b>v</b> / |<b>v</b>|<sup>2</sup><br>
   * 
   * @param onto
   *          the vector to project this vector onto
   * @return the result of the projection
   */
  public Vector2D projectionOn(Vector2D onto)
  {
    return onto.multiply(this.dotProduct(onto) / onto.getMagnitudeSquared());
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
   * Adds this vector to the given one, and returns the result.
   * 
   * @param other
   *          the vector to add to this one
   * @return the resulting vector
   */
  public Vector2D add(Vector2D other)
  {
    return new Vector2D(this.x + other.x, this.y + other.y);
  }

  /**
   * Subtracts the given vector from this one, and returns the result.
   * 
   * @param other
   *          the vector to subtract from this one
   * @return the resulting vector
   */
  public Vector2D subtract(Vector2D other)
  {
    return add(other.negate());
  }

  /**
   * Returns the vector with equal magnitude and opposite direction to this one.
   * 
   * @return the negated vector
   */
  public Vector2D negate()
  {
    return new Vector2D(-x, -y);
  }

  /**
   * Multiplies each component of this vector by the given scalar value. The
   * returned vector will have the same direction as this one, with a magnitude
   * of this vector's magnitude times the given scalar.
   * 
   * @param scalar
   *          the value to multiply this vector by
   * @return the resulting vector
   */
  public Vector2D multiply(double scalar)
  {
    return new Vector2D(x * scalar, y * scalar);
  }

  /**
   * Returns the vector orthogonal to this one in the positive
   * (counterclockwise) direction with equal magnitude.
   * 
   * @return the vector orthogonal to this one in the positive
   *         (counterclockwise) direction
   */
  public Vector2D orthogPos()
  {
    return new Vector2D(-y, x);
  }

  /**
   * Returns the vector orthogonal to this one in the negative (clockwise)
   * direction with equal magnitude.
   * 
   * @return the vector orthogonal to this one in the negative (clockwise)
   *         direction
   */
  public Vector2D orthogNeg()
  {
    return new Vector2D(y, -x);
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
    return Utils.equalsApprox(this.x, other.x, maxDelta) && Utils.equalsApprox(this.y, other.y, maxDelta);
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
