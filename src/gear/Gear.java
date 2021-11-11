package gear;

import player.Player.PlayerBuilder;

/**
 * A blue print for all the types of gears.
 * This can be used to extend the implementation if we want to add more types 
 * of gears.
 */
public interface Gear extends Comparable<Gear> {
  
  /**
   * Name of the gear.
   * @return A string value for gear name.
   */
  public String getGearName();

  /**
   * Strength offered by a gear.
   * @return integer value.
   */
  public int getStrength();
  
  /**
   * Constitution offered by a gear.
   * @return integer value.
   */
  public int getConstitution();
  
  /**
   * Dexterity offered by a gear.
   * @return integer value.
   */
  public int getDexterity();
  
  /**
   * Charisma offered by a gear.
   * @return integer value.
   */
  public int getCharisma();
  
  /**
   * Used to equip a character with a gear.
   * @param pb A PlayerBuilder object.
   */
  void sendItemToPlayer(PlayerBuilder pb);
  
  /**
   * Size of the belt.
   * By default it is 0 for all weapons.
   * @return integer value of a belt size.
   */
  public int getBeltSize();
  
  /**
   * Size of the belt as a string.
   */
  public String getBeltSizeString();
}
