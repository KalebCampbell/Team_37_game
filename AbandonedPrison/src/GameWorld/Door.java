package GameWorld;

public class Door {

  private int id;
  private String direction;
  private boolean locked;
  private String type;

  /**
   * Door constructor.
   * @param id door id
   * @param direction door direct
   * @param locked if locked
   * @param type type of door
   */
  public Door(int id, String direction, boolean locked, String type) {

    this.setId(id);
    this.setDirection(direction);
    this.setLocked(locked);
    this.setType(type);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
