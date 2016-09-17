package hack_isu_f16.game;

import java.awt.image.BufferedImage;

public class Sprite
{
  private BufferedImage[] frames;
  private int currFrame;
  
  public Sprite(BufferedImage[] frames)
  {
    this.frames = frames;
    currFrame = 0;
  }
  
  public void nextFrame()
  {
    currFrame = (currFrame + 1) % frames.length;
  }
  
  public BufferedImage getImage()
  {
    return frames[currFrame];
  }
}
