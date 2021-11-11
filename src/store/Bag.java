package store;


import gear.Belts;
import gear.FootWear;
import gear.Gear;
import gear.HeadGear;
import gear.Potions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A bag contains all the items/gears that will be assigned to a player. A bag has a minimum of 5
 * headgears, 5 footwears, 15 potions and 15 belts.
 */
public class Bag implements IBag {

  private final List<Gear> bag;

  private Bag(List<Gear> headGear, List<Gear> potions, List<Gear> belts, List<Gear> footWear)
      throws IllegalArgumentException {

    if (headGear == null || potions == null || belts == null || footWear == null) {
      throw new IllegalArgumentException("Enter valid inputs");
    }
    if (headGear.contains(null) || potions.contains(null) || belts.contains(null)
        || footWear.contains(null)) {
      throw new IllegalArgumentException("Enter valid data");
    }

    this.bag = new ArrayList<>(); 
    this.bag.addAll(headGear); 
    this.bag.addAll(potions);
    this.bag.addAll(belts);
    this.bag.addAll(footWear);
  }

  /**
   * Creating a bagBuilder object that will be added to the bag.
   * 
   * @return
   */
  public static BagBuilder getBuilder() {
    return new BagBuilder();
  }

  /**
   * A builder class that helps creating a bag.
   */
  public static class BagBuilder {
    private final List<Gear> headGear;
    private final List<Gear> potions;
    private final List<Gear> belts;
    private final List<Gear> footWear;

    private BagBuilder() {
      headGear = new ArrayList<Gear>();
      potions = new ArrayList<Gear>();
      belts = new ArrayList<Gear>();
      footWear = new ArrayList<Gear>();
    }

    /**
     * Add a headgear to the bag.
     * 
     * @param name Name of the head gear.
     * @param constitution An integer value for the ability.
     * @return An object of type BagBuilder.
     */
    public BagBuilder addHeadGear(String name,int constitution) {
      this.headGear.add(new HeadGear(name, constitution));
      return this;
    }

    /**
     * Add potion to the bag.
     * 
     * @param name Name of the potion.
     * @param strength An integer value for the ability.
     * @param constitution An integer value for the ability.
     * @param dexterity An integer value for the ability.
     * @param charisma An integer value for the ability.
     * @return An object of type BagBuilder.
     */
    public BagBuilder addPotion(String name, int strength, int constitution, int dexterity,
        int charisma) {
      this.potions.add(new Potions(name, strength, constitution, dexterity, charisma));
      return this;
    }

    /**
     * Add belt to the bag.
     * 
     * @param name Name of the belt.
     * @param strength An integer value for the ability.
     * @param constitution An integer value for the ability.
     * @param dexterity An integer value for the ability.
     * @param charisma An integer value for the ability.
     * @return An object of type BagBuilder.
     */
    public BagBuilder addBelt(String name, int strength, int constitution, int dexterity,
        int charisma, String beltSize) {
      this.belts.add(new Belts(name, strength, constitution, dexterity, charisma, beltSize));
      return this;
    }

    /**
     * Add footwear to the bag.
     *  
     * @param name Name of the footwear.
     * @param dexterity An integer value for the ability.
     * @return An object of type BagBuilder.
     */
    public BagBuilder addFootWear(String name, int dexterity) {
      this.footWear.add(new FootWear(name, dexterity));
      return this;
    }

    /**
     * A method that helps build the Bag.
     * 
     * @return An object of type Bag.
     * @throws IllegalStateException If number of items added do not match the requirement we throw
     *         this exception.
     */
    public IBag build() throws IllegalStateException {
      if (headGear.size() < 5 || potions.size() < 15 || belts.size() < 15 || footWear.size() < 5) {
        throw new IllegalStateException(
            "The bag must a minimum of 5 headgears, 5 footwears, 15 potions and 15 belts");
      }
      return new Bag(headGear, potions, belts, footWear);
    }
  }
  
  @Override
  public String toString() {
    String contents = "";
    for (Gear item : this.bag) {
      contents += item.toString() + "\n";
    }
    return contents;
  }

  @Override
  public List<Gear> playerBattleGear() {
    List<Gear> playerBattleGear = new ArrayList<Gear>();
    for (int i = 0; i < 20; i++) {
      int random = ThreadLocalRandom.current().nextInt(0, this.bag.size());
      playerBattleGear.add(this.bag.get(random));
    }
    return new ArrayList<>(playerBattleGear);
  }
  
  @Override
  public String sortedBagItems() {
    String sortedItems = "";
    StringBuilder sb = new StringBuilder();
    Collections.sort(this.bag);
    for (Gear g : this.bag) {
      sortedItems = String.format("Items type: %s, Item name: %s", g.getClass().getSimpleName(),
          g.getGearName());
      sb.append(sortedItems + "\n");
    }
    return sb.toString();
  }

}
