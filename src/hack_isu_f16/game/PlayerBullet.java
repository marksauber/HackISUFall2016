package hack_isu_f16.game;

import hack_isu_f16.geom.Rectangle2D;
import hack_isu_f16.geom.Vector2D;

public class PlayerBullet extends Projectile
{
  private static final double VEL_MAG = 1.0;
  private static final Rectangle2D BOUND_BOX = new Rectangle2D(0, 0, 16, 16);

  protected PlayerBullet(Vector2D pos, Vector2D direction)
  {
    super(pos, direction.multiply(VEL_MAG / direction.getMagnitude()), Vector2D.ZERO, BOUND_BOX);
  }

}
