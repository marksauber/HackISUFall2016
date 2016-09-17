package hack_isu_f16.game;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GameWorld
{
  enum TileType
  {
    AIR,
    FALLING,
    BREAKABLE,
    NORMAL
  }

  /**
   * The tiles of the game world. LinkedLists are used to allow deque operations
   * to be performed in constant time if necessary.
   */
  private LinkedList<TileType>[] tiles;
  private Set<Entity> entities;

  private static final int HEIGHT = 24;

  public GameWorld()
  {
    tiles = (LinkedList<TileType>[]) new LinkedList[HEIGHT];
    for (int i = 0; i < tiles.length; ++i)
    {
      tiles[i] = new LinkedList<>();
    }
    entities = new HashSet<>();
  }
}
