package gameworld;

/**
 * Combines x,y into a single object.
 * 
 * 
 * @author Michael Vincent
 *
 */
public class Location {
  
  // Checkstyle doesn't like x.
  private int xx;
  private int yy;

  public Location(int x, int y) {
    this.setX(x);
    this.setY(y);
  }

  public int getY() {
    return yy;
  }

  public void setY(int y) {
    this.yy = y;
  }

  public int getX() {
    return xx;
  }

  public void setX(int x) {
    this.xx = x;
  }

}