package gear;

import player.Player.PlayerBuilder;

/**
 * A headgear affects a player's constitution. 
 * A player can only one headgear at a time.
 * Creating an item of type headgear.
 */
public class HeadGear extends AbstractGear {

  /**
   * A constructor that creates a new headgear object. 
   * 
   * @param name Name of the headgear.
   * @param constitution Assigning an integer if it affects constitution of a player.
   */
  public HeadGear(String name, int constitution) {
    super(name, 0, constitution, 0, 0);   
  }
   
  /**
   * Creating a constructor to get a deep copy of the object.
   * 
   * @param g The headgear that is being passed.
   */
  public HeadGear(Gear g) {
    super(g);
  }

  @Override
  public void sendItemToPlayer(PlayerBuilder pb) {
    pb.addHeadGear(this);
  }
  
  @Override
  protected int compareToHeadGear() {
    return 0;
  }
  
  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear aGear = (AbstractGear) o;
      if (aGear.compareToHeadGear() == 0) {
        return this.getGearName().compareTo(aGear.getGearName());
      }
    }
    return -1;
  }
  
}
