package driver;

import battle.BattleGround;
import battle.IBattleGround;
import player.IPlayer;
import player.Player;
import random.RandomGenerator;
import random.RandomInterface;
import store.Bag;
import store.IBag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The code comes together here and allows the gamers to battle.
 */
public class Driver {

  /**
   * Adding a minimum of 40 items to the bag. Bag contains 25% (10) of the items which have negative
   * effect on players.
   * 
   * @return
   */
  public static IBag fillBag() {
    IBag bag = Bag.getBuilder().addHeadGear("killHat", -2).addHeadGear("lightJapi", 3)
        .addHeadGear("partyHat", 2).addHeadGear("BeretLife", 1).addHeadGear("baseballHat", 4)
        .addPotion("Magic", 2, 0, 0, 0).addPotion("jitterJuice", 0, 0, 3, 0)
        .addPotion("moonlight", 0, 2, 0, 0).addPotion("MagicMoonlight", 0, 0, 0, -2)
        .addPotion("Aroma", -3, 0, 0, 0).addPotion("Dhalia", 0, 0, 0, 3)
        .addPotion("DragonBreath", 0, 0, 4, 0).addPotion("Potent", 0, 4, 0, 0)
        .addPotion("Dark", 4, 0, 0, 0).addPotion("DarkMagic", 0, 2, 0, 0)
        .addPotion("Fairies", 0, 0, -2, 0).addPotion("Ninja", 0, 0, 0, 2)
        .addPotion("MoonChild", 0, 0, 2, 0).addPotion("killing", 0, 0, -3, 0)
        .addPotion("healing", 0, 3, 0, 0).addPotion("bitterBrews", 3, 0, 0, 0)
        .addBelt("Punisher", -2, 3, 0, 0, "small").addBelt("Super", 0, 3, 2, 0, "medium")
        .addBelt("batman", 0, 0, 1, 3, "large").addBelt("wonder", 3, 0, 2, 0, "small")
        .addBelt("kiss", 0, 2, 0, 2, "medium").addBelt("power", 2, 0, 0, 1, "small")
        .addBelt("light", 3, -1, 0, 0, "medium").addBelt("destroy", 3, 0, 3, 0, "large")
        .addBelt("king", 4, 0, -2, 0, "large").addBelt("queen", 0, 3, 4, 0, "large")
        .addBelt("minister", 0, 3, 0, 3, "large").addBelt("crazy", 0, 0, 2, 3, "medium")
        .addBelt("armagadon", 3, 0, 0, 3, "large").addBelt("copy", 0, 2, 2, 0, "small")
        .addBelt("street", 0, 4, -1, 0, "medium").addFootWear("nuke", 3).addFootWear("rebak", 3)
        .addFootWear("adibas", -2).addFootWear("puma", 4).addFootWear("upperArmour", 4).build();
    return bag;
  }

  /**
   * Main method where the game runs.
   * 
   * @param args Not used.
   * @throws IOException Throws exception when input or output values are not as desired.
   */
  public static void main(String[] args) throws IOException {

    String input;
    do {
      System.out.println(
          "********************************ART OF FIGHTING***********************************\n");

      System.out.println("WELCOME. \n\nCreate 2 players"
          + " with unique abilities.\nEnhance their features by equpping special gears"
          + " and providing weapons. \nPlayers fight till death.\n");

      System.out.println("In this game you will be provided a bag filled with items that can "
          + " either enhance your abilities or make you weaker.\n"
          + "The Armory contains unique weapons and you will be assigned one to survive.\n\n");

      System.out.println("********************************************" 
          + "***********************************************\n");
      System.out.println("ITEMS AVAILABLE TO YOU ARE: \n");
      IBag bag = fillBag();
      
      System.out.println(bag.sortedBagItems()); 

      System.out.println("*******************************************"
          + "************************************************\n"); 

      System.out.println("Are you ready for some unreal entertainment?\n\n");

      System.out.println("Creating your players...\n");
      
      RandomInterface r = new RandomGenerator();
      IPlayer raava = Player.getBuilder().setPlayerName("Raava").addItems(bag.playerBattleGear())
          .addTesterRandom(r).build();

      IPlayer vaatu = Player.getBuilder().setPlayerName("Vaatu").addItems(bag.playerBattleGear())
          .addTesterRandom(r).build();

      System.out.println("Players sucessfully created\n");

      IBattleGround battle = new BattleGround(raava, vaatu,r);

      System.out.println("**************************PLAYER BASE VALUES*************************\n");
      System.out.println(battle.playerBasic());

      System.out.println("*********************PLAYER ALL ITEMS ASSIGNED***********************\n");
      System.out.println("ALL ITEMS ASSIGNED TO RAAVA");
      System.out.println(raava.allRandomlyAssignedGears());
      System.out.println("\nALL ITEMS ASSIGNED TO VAATU");
      System.out.println(vaatu.allRandomlyAssignedGears());

      System.out.println(
          "\n**********************PLAYER UPDATED VALUES AFTER POTIONS*************************\n");
      System.out.println("PLAYER RAAVA DETAILS AFTER UPDATING ABILITIES");
      System.out.println(raava.playerDetailsAfterEquipped());
      System.out.println("\nPLAYER VAATU DETAILS AFTER UPDATING ABILITIES");
      System.out.println(vaatu.playerDetailsAfterEquipped());

      System.out.println(
          "**********************************BATTLE BEGINS***********************************");
      System.out.println("BATTLE STARTS BASED ON BASE CHARISMA\n"); 
      System.out.println(battle.fight());

      System.out.println("***************************************"
          + "****************************************************\n");

      System.out.println("Type Y for revenge or N to quit ");

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine();
    }
    while (input.equals("Y"));
    
    System.out.println("Thank you for playing! Come back soon.");
    

    //    if (input.equals("Y")) {
    //      IPlayer raava1 =
    //          Player.getBuilder().setPlayerName("Raava").addItems(bag.playerBattleGear()).build();
    //      IPlayer vaatu1 =
    //          Player.getBuilder().setPlayerName("Vaatu").addItems(bag.playerBattleGear()).build();
    //
    //      System.out.println("Players sucessfully created\n");
    //
    //      BattleGround battle1 = new BattleGround(raava1, vaatu1,r);
    //
    //      System.out
    //          .println("*************************PLAYER BASE VALUES**************************\n");
    //      System.out.println(battle1.playerBasic());
    //
    //      System.out
    //          .println("********************PLAYER ALL ITEMS ASSIGNED************************\n");
    //      System.out.println("ALL ITEMS ASSIGNED TO RAAVA");
    //      System.out.println(raava1.allRandomlyAssignedGears());
    //      System.out.println("\nALL ITEMS ASSIGNED TO VAATU");
    //      System.out.println(vaatu1.allRandomlyAssignedGears());
    //
    //      System.out.println(
    //          "\n******************PLAYER UPDATED VALUES AFTER POTIONS***********************\n");
    //      System.out.println("PLAYER RAAVA DETAILS AFTER UPDATING ABILITIES");
    //      System.out.println(raava1.playerDetailsAfterEquipped());
    //      System.out.println("\nPLAYER VAATU DETAILS AFTER UPDATING ABILITIES");
    //      System.out.println(vaatu1.playerDetailsAfterEquipped());
    //
    //      System.out.println(
    //          "********************************BATTLE BEGINS***********************************");
    //      System.out.println("BATTLE STARTS BASED ON BASE CHARISMA\n");
    //      System.out.println(battle1.fight());
    //
    //      System.out.println("******************************************"
    //          + "*************************************************\n");
    //    } else {
    //      System.out.println("Thank you for playing! Come back soon.");
    //    }
  }
}
