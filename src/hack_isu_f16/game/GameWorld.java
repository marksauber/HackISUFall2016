package hack_isu_f16.game;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import hack_isu_f16.util.Utils;

public class GameWorld
{
  enum TileType
  {
    AIR, FALLING, BREAKABLE, NORMAL;

    public boolean canWalkThrough()
    {
      switch (this)
      {
        case AIR:
          return true;
        default:
          return false;
      }
    }
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

    initGen();
  }

  public void generateNextColumn()
  {
    generateNextColumn(true);
  }

  private void generateNextColumn(boolean removeFirst)
  {
    Random r = new Random();
    TileType[] values = Utils.ArrayUtils.asArray(TileType.AIR, TileType.FALLING, TileType.BREAKABLE, TileType.NORMAL);
    gen:
    for (int i = tiles.length - 1; i >= 0; --i)
    {
      LinkedList<TileType> l = tiles[i];
      // Make sure player has room to continue moving
      for (int x = 0; x < 3; ++x)
      {
        if (i + x + 1 < tiles.length && !tiles[i + x + 1].getLast().canWalkThrough())
        {
          l.addLast(TileType.AIR);
          continue gen;
        }
      }
      TileType last = l.getLast();
      int[] weights;
      switch (last)
      {
        case AIR:
          weights = new int[]
          { 12, 1, 3, 2 };
          break;
        case FALLING:
          weights = new int[]
          { 12, 5, 3, 1 };
          break;
        case BREAKABLE:
          weights = new int[]
          { 12, 1, 6, 0 };
          break;
        case NORMAL:
          weights = new int[]
          { 12, 6, 4, 8 };
          break;
        default:
          weights = new int[]
          { 0, 0, 0, 1 };
          break;
      }
      l.addLast(Utils.weightedRandom(r, weights, values));
      if (removeFirst)
      {
        l.removeFirst();
      }
    }
  }

  private void initGen()
  {
    final int WIDTH = 32;
    for (int w = 0; w < WIDTH / 4; ++w)
    {
      for (int i = 0; i < tiles.length - 1; ++i)
      {
        tiles[i].add(TileType.AIR);
      }
      tiles[tiles.length - 1].add(TileType.NORMAL);
    }
    for (int w = WIDTH / 4; w < WIDTH; ++w)
    {
      generateNextColumn(false);
    }
  }
}
