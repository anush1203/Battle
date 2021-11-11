package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * A medium sized sword that inflicts a damage of 6 - 10.
 */
public class BroadSword extends Sword {
  
  /**
   * Constructing a BroadSword for testing.
   * @param rnd An object of type RandomTester.
   */
  public BroadSword(RandomInterface rnd) {
    super(rnd);
  }

  @Override
  public int damageMightCause(IPlayer p) {
    int damageValue = rnd.getInteger(6, 10);
    return damageValue;
  }
}


