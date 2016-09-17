package hack_isu_f16.game;

import hack_isu_f16.geom.Rectangle2D;
import hack_isu_f16.geom.Vector2D;

public abstract class Entity
{
  protected Vector2D position;
  protected Vector2D velocity;
  protected Rectangle2D boundingBox;

  protected Entity(Rectangle2D boundBox)
  {
    this(Vector2D.ZERO, boundBox);
  }

  protected Entity(Vector2D initialPos, Rectangle2D boundBox)
  {
    this(initialPos, Vector2D.ZERO, boundBox);
  }

  protected Entity(Vector2D initialPos, Vector2D initialVel, Rectangle2D boundBox)
  {
    this.position = initialPos;
    this.velocity = initialVel;
    this.boundingBox = boundBox.translate(-boundBox.getX1(), -boundBox.getY1());
  }

  public void update(GameWorld world)
  {
    position = position.add(velocity);
  }
  
  public Vector2D getPosition()
  {
    return position;
  }
  
  public Vector2D getVelocity()
  {
    return velocity;
  }
  
  public Rectangle2D getBoundingBox()
  {
    return boundingBox.translate(position);
  }
}
