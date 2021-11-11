package battle;

import player.IPlayer;
import random.RandomInterface;
import store.Armory;
import store.IArmory;
import weapons.Axe;
import weapons.BroadSword;
import weapons.Flail;
import weapons.IWeapons;
import weapons.Katana;
import weapons.TwoHandedSword;

import java.util.List;

/**
 * BattleGround is where the player are equipped with weapons and pit into a battle until death. We
 * determine the players powers and make them battle. The Battle can go in 3 ways. 2 of them are
 * either one of the players win or ends in a tie. Tie occurs when the battle goes over 100 rounds.
 */
public class BattleGround implements IBattleGround {

  private final IPlayer player1;
  private final IPlayer player2;

  private RandomInterface rnd;

  private IArmory armory;
  
  private int p1CombinedStrength;
  private int p2CombinedStrength;
  private int p1CombinedDexterity;
  private int p2CombinedDexterity;
  
  /** 
   * Constructing a battle ground for the 2 players.
   * 
   * @param p1 Player 1 with items equipped.
   * @param p2 Player 2 with items equipped
   */
  public BattleGround(IPlayer p1, IPlayer p2, RandomInterface r) {
    this.player1 = p1;
    this.player2 = p2;
    this.rnd = r;
   
    IWeapons katana;
    IWeapons broadSword;
    IWeapons twoHanded;
    katana = new Katana(rnd);
    broadSword = new BroadSword(rnd);
    twoHanded = new TwoHandedSword(rnd);
    IWeapons axe;
    IWeapons flail;
    axe = new Axe(rnd);
    flail = new Flail(rnd);
    
    armory = new Armory(rnd);
    
    armory.addWeaponsToArmory(katana);
    armory.addWeaponsToArmory(broadSword); 
    armory.addWeaponsToArmory(twoHanded);
    armory.addWeaponsToArmory(axe);
    armory.addWeaponsToArmory(flail);
  
    
  }

  @Override
  public void setBase() {
    player1.setBaseAbilitiesAndHealth(); 
    player2.setBaseAbilitiesAndHealth();
  }

  @Override
  public String playerBasic() {
    this.setBase();
    String print = "";
    print = String.format(
        "PLAYER NAME: %s\nPLAYER ABILITIES:\n"
            + "PLAYER STRENGTH --- %d , PLAYER CONSTITUTION --- %d, PLAYER DEXTERITY --- %d,"
            + " PLAYER CHARISMA --- %d\n\nPLAYER NAME: %s\nPLAYER ABILITIES:\n"
            + "PLAYER STRENGTH --- %d , PLAYER CONSTITUTION --- %d, PLAYER DEXTERITY --- %d,"
            + " PLAYER CHARISMA --- %d\n",
        player1.getName(), player1.getStrength(), player1.getConstitution(), player1.getDexterity(),
        player1.getCharisma(), player2.getName(), player2.getStrength(), player2.getConstitution(),
        player2.getDexterity(), player2.getCharisma());

    return print;
  }

  private void calculateCombinedStrengthAndCombinedDexterity() {
    p1CombinedStrength = player1.combinedGearStrength();
    p2CombinedStrength = player2.combinedGearStrength(); 
    p1CombinedDexterity = player1.combinedGearDexterity();
    p2CombinedDexterity = player2.combinedGearDexterity();
  }
  
  @Override
  public String fight() {
    int count = 0;
    String winner = "";
    String printRounds = "";
    StringBuilder sb = new StringBuilder(); 
    int p1Health = this.player1.getHealth();
    int p2Health = this.player2.getHealth();
    
    List<IWeapons> p1Weapon = player1.requestWeapon(armory.playerBattleWeapon());
    List<IWeapons> p2Weapon = player2.requestWeapon(armory.playerBattleWeapon());
    

    calculateCombinedStrengthAndCombinedDexterity();
    
    while ((p1Health > 0) && (p2Health > 0) && (count < 100)) {
      count++;
      int p1StrikingPower = player1.getStrength() + rnd.getInteger(1, 10) + p1CombinedStrength;
      int p2StrikingPower = player2.getStrength() + rnd.getInteger(1, 10) + p2CombinedStrength;
      
      int p1AvoidanceAbility = player1.getDexterity() + rnd.getInteger(1, 6) + p1CombinedDexterity;
      int p2AvoidanceAbility = player2.getDexterity() + rnd.getInteger(1, 6) + p2CombinedDexterity;
      
      int p1PotentialStrikingDamage =
          player1.getStrength() + p1Weapon.get(0).damageMightCause(player1);
      int p2PotentialStrikingDamage =
          player2.getStrength() + p2Weapon.get(0).damageMightCause(player2);
     
      int p1ActualDamage = Math.abs(p1PotentialStrikingDamage - player1.getConstitution());
      int p2ActualDamage = Math.abs(p2PotentialStrikingDamage - player2.getConstitution());
      
      if (player1.getCharisma() + 1 >= player2.getCharisma()) {
        if (p1StrikingPower > p2AvoidanceAbility) { 
          if (p1ActualDamage < 0) {
            p2Health -= 0;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of 0",
                count, player1.getName(), player2.getName());
            sb.append(printRounds + "\n");
          } 
          else {
            p2Health -= p1ActualDamage;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of %d",
                count, player1.getName(), player2.getName(), p1ActualDamage);
            sb.append(printRounds + "\n");
            if (p2Health <= 0) {
              break;
            }
          }
        }

        else {
          printRounds = String.format("Round: %d ----- Player: %s misses the attack", count,
              player1.getName());
          sb.append(printRounds + "\n");
        }

        if (p2StrikingPower > p1AvoidanceAbility) {
          if (p2ActualDamage < 0) {
            p1Health -= 0;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of 0",
                count, player2.getName(), player1.getName());
            sb.append(printRounds + "\n");
          } 
          else {
            p1Health -= p2ActualDamage;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of %d",
                count, player2.getName(), player1.getName(), p2ActualDamage);
            sb.append(printRounds + "\n");
            if (p1Health <= 0) {
              break;
            }
          }
        }

        else {
          printRounds = String.format("Round: %d ----- Player: %s misses the attack", count,
              player2.getName());
          sb.append(printRounds + "\n");
        }
      }

      // ----------------------------------------------------------------------------------------

      if (player2.getCharisma() > player1.getCharisma()) {
        if (p2StrikingPower > p1AvoidanceAbility) {
          if (p2ActualDamage < 0) {
            p1Health -= 0;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of 0",
                count, player2.getName(), player1.getName());
            sb.append(printRounds + "\n");
          } else { 
            p1Health -= p2ActualDamage;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of %d",
                count, player2.getName(), player1.getName(), p2ActualDamage);
            sb.append(printRounds + "\n");
            if (p1Health <= 0) {
              break;
            }
          }

        }

        else {
          printRounds = String.format("Round: %d ----- Player: %s misses the attack", count,
              player2.getName());
          sb.append(printRounds + "\n");
        }

        if (p1StrikingPower > p2AvoidanceAbility) {
          if (p1ActualDamage < 0) {
            p2Health -= 0;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of 0",
                count, player1.getName(), player2.getName());
            sb.append(printRounds + "\n");
          } else {
            p2Health -= p1ActualDamage;
            printRounds = String.format(
                "Round: %d ----- Player: %s lands a strike on Player: %s dealing a damage of %d",
                count, player1.getName(), player2.getName(), p1ActualDamage);
            sb.append(printRounds + "\n");
            if (p2Health <= 0) {
              break;
            }
          }
        }

        else {
          printRounds = String.format("Round: %d ----- Player: %s misses the attack", count,
              player1.getName());
          sb.append(printRounds + "\n");
        }
      }
    }
    if (p1Health <= 0) {
      winner = this.player2.getName();
    } else if (p2Health <= 0) {
      winner = this.player1.getName();
    } else {
      winner = "tie";
    }

    printRounds = String.format("The winner of the battle is: %s", winner);
    sb.append("\n" + printRounds + "\n");
    return sb.toString();
  }
}
