package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * A two handed sword is a heavy sword that can be used to max potential by players
 * who have a strength of more than 14. Else the damage is halved.
 */
public class TwoHandedSword extends Sword {
  
  /**
   * Constructing a TwohandedSword for testing.
   * @param rnd An object of type RandomTester.
   */
  public TwoHandedSword(RandomInterface rnd) {
    super(rnd);
  }
  
  @Override
  public int damageMightCause(IPlayer p) {
    int damageValue;
    
    if (p.getStrength() > 14) {
      damageValue = rnd.getInteger(8, 12);
    }
    else {
      damageValue = rnd.getInteger(4, 6);
    }
    return damageValue;
  }
}
