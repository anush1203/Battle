package gear;

/**
 * An enumeration that contains the different sizes of a belt.
 */
public enum BeltSize {
  
  SMALL("small"),
  MEDIUM("medium"),
  LARGE("large");

  String beltSize;
  
  private BeltSize(String beltSize) {
    this.beltSize = beltSize;
  }
  
  public String getBeltSize() {
    return this.beltSize;
  }

}
