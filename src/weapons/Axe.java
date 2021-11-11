package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * Creating a weapon called Axe. 
 * It can inflict damage between 6 to 10.
 */
public class Axe implements IWeapons {
  
  private RandomInterface rnd;
  
  /**
   * Constructing axe for testing.
   * @param rnd An object of type RandomTester.
   */
  public Axe(RandomInterface rnd) {
    this.rnd = rnd;
  }

  @Override
  public int damageMightCause(IPlayer p) {
    int damageValue = rnd.getInteger(6, 10);
    return damageValue;
  }
}
