package hack_isu_f16.geom;

public class Rectangle2D
{
  final double x1;
  final double y1;
  final double x2;
  final double y2;
  
  public Rectangle2D(double x1, double y1, double x2, double y2)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public double getX1()
  {
    return x1;
  }
  
  public double getY1()
  {
    return y1;
  }
  
  public double getX2()
  {
    return x2;
  }
  
  public double getY2()
  {
    return y2;
  }
  
  public Vector2D getCornerUL()
  {
    return new Vector2D(x1, y1);
  }
  
  public Vector2D getCornerUR()
  {
    return new Vector2D(x2, y1);
  }
  
  public Vector2D getCornerLL()
  {
    return new Vector2D(x1, y2);
  }
  
  public Vector2D getCornerLR()
  {
    return new Vector2D(x2, y2);
  }
  
  public Vector2D getCenter()
  {
    return new Vector2D((x1 + x2) / 2, (y1 + y2) / 2);
  }
  
  public double getWidth()
  {
    return x2 - x1;
  }
  
  public double getHeight()
  {
    return y2 - y1;
  }
  
  public double getArea()
  {
    return getWidth() * getHeight();
  }
  
  private boolean intersect1D(double s1, double e1, double s2, double e2)
  {
    return (s1 <= s2 && s2 <= e1) || (s1 <= e2 && e2 <= e1) || (s2 <= s1 && s1 <= e2) || (s2 <= e1 && e1 <= e2);
  }
  
  public boolean intersects(Rectangle2D other)
  {
    return intersect1D(this.x1, this.x2, other.x1, other.x2) && intersect1D(this.y1, this.y2, other.y1, other.y2);
  }
}
