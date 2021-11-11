package gear;

import player.Player.PlayerBuilder;


/**
 * Footwears are items that affect a player's dexterity.
 * Creating an item of type footwear.
 */
public class FootWear extends AbstractGear {

  /**
   * A constructor that creates a new footwear object. 
   * 
   * @param name Name of the footwear.
   * @param dexterity Assigning an integer if it affects dexterity of a player.
   */
  public FootWear(String name, int dexterity)   {
    super(name, 0, 0, dexterity, 0);
  }
  
  /**
   * Creating a constructor to get a deep copy of the object.
   * 
   * @param g The footwear that is being passed.
   */
  public FootWear(Gear g) {
    super(g);
  }

  @Override
  public void sendItemToPlayer(PlayerBuilder pb) {
    pb.addFootWear(this);
  }
  
  @Override
  protected int compareToFootWear() {
    return 0;
  }
  
  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear aGear = (AbstractGear) o;
      if (aGear.compareToFootWear() == 0) {
        return this.getGearName().compareTo(aGear.getGearName());
      }
    }
    return +1;
  }
}
