package player;

import gear.Belts;
import gear.FootWear;
import gear.Gear;
import gear.HeadGear;
import gear.Potions;
import random.RandomGenerator;
import random.RandomInterface;
import weapons.IWeapons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Player is the entity that is assigned base abilities, equipped with items, assigned a weapon and
 * pit into a fight with another player. Here is a class that creates a player.
 */
public class Player implements IPlayer {

  private String name;
  private final List<Gear> tempHeadGear;
  private final List<Gear> tempPotions;
  private final List<Gear> tempBelts;
  private final List<Gear> tempFootWear;

  private Gear playerHeadgear;
  //private final List<Gear> playerPotions;
  private final List<Gear> playerBelts;
  private Gear playerFootWear;
  private final List<IWeapons> playerWeapon;

  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private RandomInterface r;

  private int health;

  /**
   * A constructor that creates a player. This is used for implementation and for testing purposes
   * as well.
   * 
   * @param name Player name.
   * @param headGear All the headgears assigned to a player.
   * @param potions All the potions assigned to a player.
   * @param belts All the belts assigned to a player.
   * @param footWear All the footWear assigned to a player.
   * @throws IllegalArgumentException An exception is thrown when a the inputs provided are null.
   */
  public Player(String name, List<Gear> headGear, List<Gear> potions, List<Gear> belts,
      List<Gear> footWear, RandomInterface r) throws IllegalArgumentException {

    if (name == null || name.isEmpty() || headGear == null || potions == null || belts == null
        || footWear == null || r == null) {
      throw new IllegalArgumentException("Pass valid Inputs");
    }

    if (headGear.contains(null) || potions.contains(null) || belts.contains(null)
        || footWear.contains(null)) {
      throw new IllegalArgumentException("Pass valid Inputs");
    }

    this.name = name;

    this.r = r;

    tempHeadGear = new ArrayList<Gear>();
    tempPotions = new ArrayList<Gear>();
    tempBelts = new ArrayList<Gear>();
    tempFootWear = new ArrayList<Gear>();

    this.playerWeapon = new ArrayList<IWeapons>();

    for (Gear g : headGear) {
      this.tempHeadGear.add(new HeadGear(g));
    }
    for (Gear g : potions) {
      this.tempPotions.add(new Potions(g));
    }
    for (Gear g : belts) {
      this.tempBelts.add(new Belts(g));
    }
    for (Gear g : footWear) {
      this.tempFootWear.add(new FootWear(g));
    }

    playerBelts = new ArrayList<Gear>();
    // playerPotions = new ArrayList<Gear>();
  }

  /**
   * rolling 4 dice and computing the highest 3 rolls. Used to assign to player's abilities.
   * 
   * @return return an integer value. Sum of 3 best rolls.
   */
  private int rollDice() {
    int[] dice = new int[4];
    int sum = 0;

    for (int i = 0; i < 4; i++) {
      dice[i] = r.getInteger(2, 6);
    }

    Arrays.sort(dice);

    sum = dice[1] + dice[2] + dice[3];

    return sum;
  }

  @Override
  public void setBaseAbilitiesAndHealth() {
    this.strength = this.rollDice();
    this.constitution = this.rollDice();
    this.dexterity = this.rollDice();
    this.charisma = this.rollDice();

    this.health = this.strength + this.constitution + this.dexterity + this.charisma;
  }
  

  /**
   * Calling the constructor in the builder.
   * 
   * @return A call to the constructor in the builder.
   */
  public static PlayerBuilder getBuilder() {
    return new PlayerBuilder();
  }

  /**
   * A builder class that will help us create our player.
   */
  public static class PlayerBuilder {
    private String name;
    private RandomInterface r;
    public List<Gear> headGear;
    public List<Gear> potions;
    public List<Gear> belts;
    public List<Gear> footWear;

    private PlayerBuilder() {
      name = "";
      r = new RandomGenerator();
      headGear = new ArrayList<Gear>();
      potions = new ArrayList<Gear>();
      belts = new ArrayList<Gear>();
      footWear = new ArrayList<Gear>();
    }

    /**
     * Setting a player's name.
     * 
     * @param name Name to be set.
     * @return returns this object.
     * @throws IllegalArgumentException if name is null or empty
     */
    public PlayerBuilder setPlayerName(String name) throws IllegalArgumentException {
      if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("Invalid input");
      }
      this.name = name;
      return this;
    }

    /**
     * Adding 20 random items to a player from the bag.
     * 
     * @param randomItems A list of 20 randomly generated items from the bag.
     * @return A playerBuilder object.
     * @throws IllegalArgumentException Throws an exception for null values of randomItems.
     */
    public PlayerBuilder addItems(List<Gear> randomItems) throws IllegalArgumentException {
      if (randomItems == null) {
        throw new IllegalArgumentException("Invalid gear for player");
      }
      for (Gear gear : randomItems) {
        if (gear == null) {
          throw new IllegalArgumentException("Invalid gear for player");
        }
        gear.sendItemToPlayer(this);
      }
      return this;
    }

    /**
     * Assigning headgears to a player.
     * 
     * @param headGear The headgear that is being assigned to the player.
     * @return A playerBuilder object.
     * @throws IllegalArgumentException When headGear value is null.
     */
    public PlayerBuilder addHeadGear(Gear headGear) throws IllegalArgumentException {
      if (headGear == null) {
        throw new IllegalArgumentException("Invalid input");
      }
      this.headGear.add(headGear);
      return this;
    }

    /**
     * Assigning potions to a player.
     * 
     * @param potions The potion that is being assigned to the player.
     * @return A playerBuilder object.
     * @throws IllegalArgumentException When potion value is null.
     */
    public PlayerBuilder addPotions(Gear potions) throws IllegalArgumentException {
      if (potions == null) {
        throw new IllegalArgumentException("Invalid input");
      }
      this.potions.add(potions);
      return this;
    }

    /**
     * Assigning belts to a player.
     * 
     * @param belts The belt that is being assigned to the player.
     * @return A playerBuilder object.
     * @throws IllegalArgumentException When belt value is null.
     */
    public PlayerBuilder addBelts(Gear belts) throws IllegalArgumentException {
      if (belts == null) {
        throw new IllegalArgumentException("Invalid input");
      }
      this.belts.add(belts);
      return this;
    }

    /**
     * Assigning footwear to a player.
     * 
     * @param footWear The footwear that is being assigned to the player.
     * @return A playerBuilder object.
     * @throws IllegalArgumentException When footwear value is null.
     */
    public PlayerBuilder addFootWear(Gear footWear) throws IllegalArgumentException {
      if (footWear == null) {
        throw new IllegalArgumentException("Invalid value for footwear for character");
        
      }
      this.footWear.add(footWear);
      return this;
    }

    /**
     * Random number generator for testing purposes.
     * 
     * @param r An object of type RandomTester.
     * @return An object of PlayerBuilder class.
     */
    public PlayerBuilder addTesterRandom(RandomInterface r) {
      this.r = r;
      return this;
    }

    /**
     * Building a player with these assigned gears and name.
     * 
     * @return Player object is returned.
     */
    public Player build() {
      return new Player(name, headGear, potions, belts, footWear, r);
    }
  }

  @Override
  public String getName() {
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
  public int getHealth() {
    return this.health;
  }

  private Gear getWearableHeadGear() {
    if (!this.tempHeadGear.isEmpty()) {
      this.playerHeadgear = this.tempHeadGear.get(0);
    }
    return this.playerHeadgear;
  }

  // private List<Gear> getDrinkablePotions() {
  // this.playerPotions.addAll(this.tempPotions);
  // return new ArrayList<>(this.playerPotions);
  // }

  private List<Gear> getWearableBelts() {
    int units = 0;
    for (Gear g : this.tempBelts) {
      if (units + g.getBeltSize() < 10) {
        this.playerBelts.add(g);
        units += g.getBeltSize();
      }
    }
    return new ArrayList<>(this.playerBelts);
  }

  private Gear getWearableFootWear() {
    
    if (this.tempFootWear.size() > 0) {
      playerFootWear =  this.tempFootWear.get(0);
    }
    
    else {
      playerFootWear = new FootWear("abc",0);
    }
   
    return playerFootWear;
  }

  @Override
  public List<IWeapons> requestWeapon(IWeapons w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("Invalid argument");
    }
    if (w.getClass().getSimpleName().equals("Katana")) {
      this.playerWeapon.add(w);
      this.playerWeapon.add(w);
    } else {
      this.playerWeapon.add(w);
    }
    return new ArrayList<>(this.playerWeapon);
  }

  private int computeCombinedGearStrength() {
    int combinedStrength = 0;

    // this.getDrinkablePotions();
    this.getWearableBelts();

    for (Gear g : this.tempPotions) {
      combinedStrength += g.getStrength();
    }
    for (Gear g : this.playerBelts) {
      combinedStrength += g.getStrength();
    }
    return combinedStrength;
  }

  @Override
  public int combinedGearStrength() {
    return this.computeCombinedGearStrength();
  }

  private int computeCombinedGearDexterity() {
    int combinedDexterity = 0;

    // this.getDrinkablePotions();
    this.getWearableBelts();

    for (Gear g : this.tempPotions) {
      combinedDexterity += g.getDexterity();
    }
    for (Gear g : this.playerBelts) {
      combinedDexterity += g.getDexterity();
    }

    combinedDexterity += this.playerFootWear.getDexterity();

    return combinedDexterity;
  }

  @Override
  public int combinedGearDexterity() {
    return this.computeCombinedGearDexterity();
  }

  @Override
  public int strengthAfterPotions() {
    int newStrength = this.getStrength();
    for (Gear g : this.tempPotions) {
      newStrength += g.getStrength();
    }
    return newStrength;
  }

  @Override
  public int constitutionAfterPotions() {
    int newConstitution = this.getConstitution();
    for (Gear g : this.tempPotions) {
      newConstitution += g.getConstitution();
    }
    return newConstitution;
  }

  @Override
  public int dexterityAfterPotions() {
    int newDexterity = this.getDexterity();
    for (Gear g : this.tempPotions) {
      newDexterity += g.getDexterity();
    }
    return newDexterity;
  }

  @Override
  public int charismaAfterPotions() {
    int newCharisma = this.getCharisma();
    for (Gear g : this.tempPotions) {
      newCharisma += g.getCharisma();
    }
    return newCharisma;
  }

  @Override
  public String allRandomlyAssignedGears() {
    String allGearsInOrder = "";
    StringBuilder sb = new StringBuilder();

    List<Gear> allRandomGears = new ArrayList<>();
    allRandomGears.addAll(this.tempHeadGear);
    allRandomGears.addAll(this.tempPotions);
    allRandomGears.addAll(this.tempBelts);
    allRandomGears.addAll(this.tempFootWear);

    Collections.sort(allRandomGears);

    for (Gear g : allRandomGears) {
      allGearsInOrder = String.format("Items type: %s, Item name: %s", g.getClass().getSimpleName(),
          g.getGearName());
      sb.append(allGearsInOrder + "\n");
    }

    String playerAllGears = "";

    playerAllGears =
        String.format("PLAYER NAME: %s\nALL ITEMS EQUIPPED:\n%s\n", this.getName(), sb.toString());

    return playerAllGears;
  }

  @Override
  public String playerDetailsAfterEquipped() {
    String allGearsInOrder = "";
    StringBuilder sb = new StringBuilder();

    List<Gear> playerGears = new ArrayList<>();
    playerGears.add(this.getWearableHeadGear());
    playerGears.addAll(this.tempPotions);
    playerGears.addAll(this.getWearableBelts());
    playerGears.add(this.getWearableFootWear());
    Collections.sort(playerGears);

    for (Gear g : playerGears) {
      if (g != null) {
        allGearsInOrder = String.format("Items type: %s, Item name: %s",
            g.getClass().getSimpleName(), g.getGearName());
        sb.append(allGearsInOrder + "\n");
      }
    }

    String playerDetailsAfterEquipped = "";

    playerDetailsAfterEquipped = String.format(
        "PLAYER NAME: %s\nPLAYER ABILITIES:\n"
            + "PLAYER STRENGTH --- %d , PLAYER CONSTITUTION --- %d, PLAYER DEXTERITY --- %d,"
            + " PLAYER CHARISMA --- %d\nPLAYER ITEMS ARE: \n %s",
        this.getName(), this.strengthAfterPotions(), this.constitutionAfterPotions(),
        this.dexterityAfterPotions(), this.charismaAfterPotions(), sb.toString());

    return playerDetailsAfterEquipped;
  }
  
  @Override
  public void setPlayerHeadGearAndFootWear() {
    this.getWearableHeadGear();
    this.getWearableFootWear();
  }

}
