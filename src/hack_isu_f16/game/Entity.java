package hack_isu_f16.game;

import hack_isu_f16.geom.Vector2D;

public abstract class Entity
{
  protected Vector2D position;
  
  public abstract void update();
}
