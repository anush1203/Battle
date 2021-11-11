package random;

import java.util.Random;

/**
 * A class that is used to generate random numbers given the bounds.
 * Makes use of Random class.
 */
@SuppressWarnings("serial")
public class RandomGenerator extends Random implements RandomInterface {

  private Random rnd;
  
  /**
   * A constructor for the random generation of numbers.
   */
  public RandomGenerator() {
    rnd = new Random();
  }
  
  @Override
  public int getInteger(int l, int u) {
    return rnd.nextInt((u - l)  + 1) + l;
  }

}
