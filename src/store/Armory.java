package store;

import random.RandomInterface;
import weapons.IWeapons;

import java.util.ArrayList;
import java.util.List;

/**
 * Armory is the storage for all the weapon types. Armory contains weapons of types, katana, flail
 * and axes. Creating an armory class.
 */
public class Armory implements IArmory {

  private RandomInterface rnd;

  private final List<IWeapons> weapons;

  /**
   * A constructor that will be used during testing. Generates a fixed number making testing
   * possible.
   * 
   * @param r an object of type RandomTester.
   */
  public Armory(RandomInterface r) {
    this.weapons = new ArrayList<>();
    rnd = r;
  }

  @Override
  public List<IWeapons> addWeaponsToArmory(IWeapons w) throws IllegalArgumentException {
    if (w == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.weapons.add(w);
    return new ArrayList<>(this.weapons);
  }

  @Override
  public IWeapons playerBattleWeapon() {
    IWeapons playerBattleWeapon = null;
    int random = rnd.getInteger(0, 4);
    if (!this.weapons.isEmpty()) {
      playerBattleWeapon = this.weapons.get(random);
    }
    return playerBattleWeapon;
  }

  @Override
  public List<String> listOfWeapons() {
    List<String> weaponName = new ArrayList<>();
    if (weapons == null) {
      throw new IllegalArgumentException("No weapons");
    }
    
    for (IWeapons a : this.weapons) {
      weaponName.add(a.getClass().getSimpleName());
    }
    
    return weaponName;
  }


}
