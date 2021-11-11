package gear;

import player.Player.PlayerBuilder;

/**
 * Belts are items that can be worn by a player that can either enhance or 
 * reduce 2 abilities of a player.
 * Belts come in varied sizes. Small, Medium and Large.
 */
public class Belts extends AbstractGear {

  private String beltSize;

  /**
   * Building a belt and assigning it values that can affect a player's abilities.
   * 
   * @param name Name of the belt.
   * @param strength Assigning an integer if it affects strength of a player.
   * @param constitution Assigning an integer if it affects constitution of a player.
   * @param dexterity Assigning an integer if it affects dexterity of a player.
   * @param charisma Assigning an integer if it affects charisma of a player.
   * @param beltSize Specifying whether a belt is small, medium or large.
   */
  public Belts(String name, int strength, int constitution, int dexterity, int charisma,
      String beltSize) {
    super(name, strength, constitution, dexterity, charisma);
    if (beltSize == null || beltSize.isEmpty()) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (!beltSize.equals("small") && !beltSize.equals("medium") && !beltSize.equals("large")) {
      throw new IllegalArgumentException("Invalid Input");
    }
    this.beltSize = beltSize;
  }

  /**
   * Creating a constructor to get a deep copy of the object.
   * 
   * @param g The belt that is being passed.
   */
  public Belts(Gear g) {
    super(g);
    if (g.getBeltSizeString() == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.beltSize = g.getBeltSizeString();
  }
  
  // public Belts(String name, int strength, int constitution, int dexterity, int charisma) {
  // super(name, strength, constitution, dexterity, charisma);
  // }

  @Override
  public void sendItemToPlayer(PlayerBuilder pb) {
    pb.addBelts(this);
  }

  @Override
  public int getBeltSize() {
    if (this.beltSize == BeltSize.SMALL.beltSize) {
      return 1;
    }
    if (this.beltSize == BeltSize.MEDIUM.beltSize) {
      return 2;
    }
    if (this.beltSize == BeltSize.LARGE.beltSize) {
      return 4;
    }
    return 0;
  }

  @Override
  public String getBeltSizeString() {
    return this.beltSize;
  }

  @Override
  protected int compareToBelts() {
    return 0;
  }

  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear aGear = (AbstractGear) o;
      if (aGear.compareToBelts() == 0) {
        return this.getGearName().compareTo(aGear.getGearName());
      } else if (aGear.compareToFootWear() == 0) {
        return -1;
      } else if (aGear.compareToHeadGear() == 0) {
        return +1;
      } else if (aGear.compareToPotions() == 0) {
        return +1;
      }
    }
    return -1;
  }
}
