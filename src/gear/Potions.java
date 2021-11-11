package gear;

import player.Player.PlayerBuilder;

/**
 * Potions can affect any one of the player's abilities.
 * A player can consume unlimited number of potions.
 * Creating an item of type potions.
 */
public class Potions extends AbstractGear {

  /**
   * A constructor that creates a new potions object. 
   * 
   * @param name Name of the potions.
   * @param strength Assigning an integer if it affects strength of a player.
   * @param constitution Assigning an integer if it affects constitution of a player.
   * @param dexterity Assigning an integer if it affects dexterity of a player.
   * @param charisma Assigning an integer if it affects charisma of a player.
   */
  public Potions(String name, int strength, int constitution, int dexterity, int charisma) {
    super(name, strength, constitution, dexterity, charisma);  
  }
  
  /**
   * Creating a constructor to get a deep copy of the object.
   * 
   * @param g The potions that is being passed.
   */
  public Potions(Gear g) {
    super(g);
  }

  @Override
  public void sendItemToPlayer(PlayerBuilder pb) {
    pb.addPotions(this);
  }
  
  @Override
  protected int compareToPotions() {
    return 0;
  }
  
  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear aGear = (AbstractGear) o;
      if (aGear.compareToPotions() == 0) {
        return this.getGearName().compareTo(aGear.getGearName());
      }
      else if (aGear.compareToHeadGear() == 0) {
        return +1;
      }
    }
    return -1;
  }

}
