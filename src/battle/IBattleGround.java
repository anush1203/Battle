package battle;

/**
 * A blue print to construct different types of battle fields.
 */
public interface IBattleGround {

  /**
   * Set the base abilities of the players.
   */
  public void setBase();
  
  /**
   * Display the player's basic abilities.
   * @return A string of abilities of both the players.
   */
  public String playerBasic();
  
  /**
   * The actual fighting between 2 players takes place here.
   * Calculating striking powers, avoidance abilities, potential striking damages 
   * and actual damages is done here.
   * Player is assigned a weapon to fight with throughout the battle. 
   * The player with higher charisma gets to take the first strike. 
   * Added +1 for the player1 charisma for testing purposes.
   * 
   * @return Description of each round and who wins.
   */
  public String fight();
}
