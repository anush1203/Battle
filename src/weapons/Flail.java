package weapons;

import player.IPlayer;
import random.RandomInterface;

/**
 * A weapon that can only be used to max potential if player 
 * dexterity is greater than 14. 
 * If not attack damage falls by half.
 */
public class Flail implements IWeapons {
  
  private RandomInterface rnd;
  
  /**
   * Constructing a flail for testing.
   * @param rnd An object of type RandomTester.
   */
  public Flail(RandomInterface rnd) {
    this.rnd = rnd;
  }
  
  @Override
  public int damageMightCause(IPlayer p) {
    int damageValue;
    
    if (p.getDexterity() > 14) {
      damageValue = rnd.getInteger(8, 12);
    }
    else {
      damageValue = rnd.getInteger(4, 6);
    }
    return damageValue;
  }
  
}
