package gear;

/**
 * An abstract class for all the various types of items/gears. Having this abstract class for gears
 * avoids code duplication. We also use this to order the Gears in a certain order.
 */
public abstract class AbstractGear implements Gear {

  private final String name;
  private final int strength;
  private final int constitution;
  private final int dexterity;
  private final int charisma;


  /**
   * A constructor for the abstract class. It sets the items name and abilities. These abilities can
   * either increase or decrease a player's abilities.
   * 
   * @param name Name of the gear.
   * @param strength An integer value that either increases or decreases a player's strength.
   * @param constitution An integer value that either increases or decreases a player's
   *        constitution.
   * @param dexterity An integer value that either increases or decreases a player's dexterity.
   * @param charisma An integer value that either increases or decreases a player's charisma.
   * 
   * @throws IllegalArgumentException Throws an exception when the gear does not have a name.
   */
  public AbstractGear(String name, int strength, int constitution, int dexterity, int charisma)
      throws IllegalArgumentException { 
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.name = name;
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;
  }

  /**
   * A constructor that allows to get the deep copy of the gears.
   * 
   * @param g An item/ gear.
   * @throws IllegalArgumentException Throws an exception when gear being passed is null.
   */
  public AbstractGear(Gear g) throws IllegalArgumentException {
    if (g == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.name = g.getGearName();
    this.strength = g.getStrength();
    this.constitution = g.getConstitution();
    this.dexterity = g.getDexterity();
    this.charisma = g.getCharisma();
  }

  @Override
  public String getGearName() {
    return this.name;
  }

  @Override
  public int getStrength() {
    return this.strength;
  }

  @Override
  public int getConstitution() {
    return this.constitution;
  }

  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  @Override
  public int getCharisma() {
    return this.charisma;
  }

  @Override
  public int getBeltSize() {
    return 0;
  }

  @Override
  public String getBeltSizeString() {
    return "Sorry! Only belts can have sizes.";
  }

  @Override
  public int compareTo(Gear o) {
    return 0;
  }

  protected int compareToHeadGear() {
    return -1;
  }

  protected int compareToPotions() {
    return -1;
  }

  protected int compareToBelts() {
    return -1;
  }

  protected int compareToFootWear() {
    return -1;
  }

  @Override
  public String toString() {
    return String.format("Name: %s, Strength: %s, Constitution: %s, Dexterity: %s, Charisma: %s",
        this.name, this.getStrength(), this.getConstitution(), this.getDexterity(),
        this.getCharisma());
  }
}
