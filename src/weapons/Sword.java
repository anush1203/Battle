package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * A sword can be of 3 types. 
 * katna, Broadsword and two handed sword are the 3 types.
 * I have made use of union type. 
 * In the future if the user wants to assign a general sword he can do it.
 */
public class Sword implements IWeapons {
  
  protected RandomInterface rnd;
  
  /**
   * Constructing a Sword for testing.
   * @param rnd An object of type RandomTester.
   */
  public Sword(RandomInterface rnd) {
    this.rnd = rnd;
  }

  @Override
  public int damageMightCause(IPlayer p) {
    return 0;
  }

}
