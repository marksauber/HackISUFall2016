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
    this.boundingBox = boundBox;
  }

  public void update()
  {
    position = position.add(velocity);
  }
}
