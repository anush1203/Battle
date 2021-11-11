package random;

/**
 * A blue print for random classes. 
 * This is used to ensure we are able to test our implementation.
 */
public interface RandomInterface {

  /**
   * To get a random integer value between the specified value.
   * 
   * @param l The lower limit or the lower range.
   * @param u The upper limit or the upper range.
   * @return
   */
  public int getInteger(int l, int u);
  
}
