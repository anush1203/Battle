package store;

import weapons.IWeapons;

import java.util.List;

/**
 * A blueprint for creating a new Armory.
 * If a user wants to implement a new armory he 
 * must have the following methods.
 */
public interface IArmory {

  /**
   * A list of 5 different types of weapons.
   * @param w The weapon type to be stored in the armory
   * @return A list of all the weapons added.
   */
  List<IWeapons> addWeaponsToArmory(IWeapons w);
  
  /**
   * A randomly generated weapon to be assigned to a player 
   * upon request.
   * @return A weapon object.
   */
  IWeapons playerBattleWeapon();

  /**
   * List of weapons in the armory.
   */
  List<String> listOfWeapons();
}
