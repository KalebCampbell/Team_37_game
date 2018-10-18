package Renderer;

import java.awt.Color;

/**
 * Abstract class representing an object in a 3D environment.
 * 
 * @author Joel Harris
 */
public abstract class AbstractPiece {

  protected Mesh mesh;
  protected Point3D position;
  protected Color color;

  /**
   * Translates the object by the delta values provided.
   * 
   * @param x
   *          delta x
   * @param y
   *          delta y
   * @param z
   *          delta z
   */
  public void translate(float x, float y, float z) {
    mesh.translate(x, y, z);
    position.translate(x, y, z);
  }

  /**
   * Rotates the object 90 degrees to the left.
   */
  public void rotateLeft() {
    mesh.rotateLeft();
    position.rotateLeft();
  }

  /**
   * Rotates the object 90 degrees to the right.
   */
  public void rotateRight() {
    mesh.rotateRight();
    position.rotateRight();
  }

  /**
   * Sets the color of each Polygon3D in this object to the objects color.
   * 
   * @param color
   *          the color to be set
   */
  public void setPolygonColors(Color color) {
    Polygon3D[] polygons = mesh.getPolygons();
    for (int i = 0; i < polygons.length; i++) {
      polygons[i].setColor(color);
    }
  }

  /**
   * @return the center position
   */
  public Point3D getPosition() {
    return position;
  }

  /**
   * @return the mesh
   */
  public Mesh getMesh() {
    return mesh;
  }

  /**
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * @param color
   *          the color to set
   */
  public void setColor(Color color) {
    this.color = color;
  }
}
