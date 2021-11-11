package store;

import gear.Gear;

import java.util.List;

/**
 * A bag that contains all the items that will be 
 * assigned to a player.
 */
public interface IBag {
  
  /**
   * Equipping a player with random 20 items from the bag of equipment.
   * 
   * @return A list of 20 items that may include headgear, footwears, potions and belts.
   */
  List<Gear> playerBattleGear();
  
  /**
   * Items in the bag in sorted order.
   * @return A sorted string of bag items.
   */
  String sortedBagItems();
}
