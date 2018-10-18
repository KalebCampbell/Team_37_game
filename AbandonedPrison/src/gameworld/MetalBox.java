package gameworld;

public class MetalBox extends Container {

  public MetalBox(String containerName, int containerId, String containerDescription,
      Location containerLocation) {
    super(containerName, containerId, containerDescription, containerLocation);
  }

  /**
   * own implementation of the open method.
   * 
   * @return boolean if open.
   */
  public boolean open() {

    if (this.isLocked) {
      System.out.println("A metal Clunk the chest is locked");
      return false;
    } else {
      System.out.println("A metal Clunk as the chest opens");
      return true;
    }
  }
  /**
  * Unlock the box.
  * @param item type.
  * @return true/false if it opened.
  */
  
  public boolean unlock(Item item) {
    if (this.isLocked) {
      System.out.println("A metal Clunk the chest is locked");
      if (item.getItemId() == this.getId()) {
        this.isLocked = false;
        System.out.println("The key fits! The metal chest is open!");
      } else if (item.getItemId() != this.getId()) {
        this.isLocked = true;
        System.out.println("The key does not fit, try another!");
      }
    }
    return isLocked;
  }

  public String getType() {
    return "MetalBox";

  }

}
