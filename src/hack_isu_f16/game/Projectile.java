package hack_isu_f16.game;

import hack_isu_f16.geom.Rectangle2D;
import hack_isu_f16.geom.Vector2D;

public abstract class Projectile extends Entity
{
  protected Vector2D acceleration;

  protected Projectile(Vector2D pos, Vector2D vel, Vector2D accel, Rectangle2D boundBox)
  {
    super(pos, vel, boundBox);
    this.acceleration = accel;
  }

  public void update()
  {
    super.update();
    velocity.add(acceleration);
  }
}
