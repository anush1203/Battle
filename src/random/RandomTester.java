package random;

/**
 * Generating a fixed number wherever random class is being used.
 * This is used to make the testing process doable.
 */
public class RandomTester implements RandomInterface {

  @Override
  public int getInteger(int l, int u) {
    return (u - 1);
  }

}
