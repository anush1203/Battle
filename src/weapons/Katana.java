package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * A katana is a light sword. A player can be assigned 2 of these.
 * It inflicts a damage between 4-6.
 */
public class Katana extends Sword {

  /**
   * Constructing a katana for testing.
   * @param rnd An object of type RandomTester.
   */
  public Katana(RandomInterface rnd) {
    super(rnd);
  }

  @Override
  public int damageMightCause(IPlayer p) {
    int damageValue = rnd.getInteger(4,6);
    return damageValue;
  }
}
