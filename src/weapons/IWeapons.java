package weapons;

import player.IPlayer;

/**
 * A blueprint for creating weapons.
 * A weapon must always cause damage and implementing 
 * this interface ensures that.
 */
public interface IWeapons {

  /**
   * Randomly generates damage value between the specified range.
   * @param p Takes player object as a parameter.
   * @return return a randomly generated integer value.
   */
  public int damageMightCause(IPlayer p);
}
