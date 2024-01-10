//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Impostor Among Us Video Game
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import processing.core.PImage;

/**
 * This class contains methods and call back methods used by 
 * the Processing library to run our Impostor Among Us game
 * 
 * @author Mathom Johnson
 */
public class SpaceStation {
  
  private static PImage background;
  private static Amogus[] players;
  private static int NUM_PLAYERS = 8;
  private static int impostorIndex;
  
  /**
   * This method is the first to run after the main method is executed.
   * It loads the background image. It also initializes a 
   * new Amogus array and creates a new Amogus at its first index.
   * Finally, it creates a random index for our imposter and prints it to 
   * the screen.
   */
  public static void setup() {
    background = Utility.loadImage("images"+File.separator+"background.jpeg");
    players = new Amogus[NUM_PLAYERS];
    players[0] = new Amogus(1 + Utility.randGen.nextInt(3));
    impostorIndex = 1 + Utility.randGen.nextInt(NUM_PLAYERS - 1);
    System.out.println("Imposter Index: " + impostorIndex);
  }
  
  /**
   * This method is constantly being executed when the program is running.
   * It draws the background image to the screen. It also draws players to
   * the screen and check if they are overlapping with an impostor. If they 
   * are, then they are 'unalived'.
   */
  public static void draw() {
    Utility.image(background, 600, 500);
    
    // loop through all players
    for (int i = 0; i < NUM_PLAYERS; i++) {
      if (players[i] != null) {
        
        // loop through all players for each player
        // checks if any players are touching an impostor
        for (int j = 0; j < NUM_PLAYERS; j++) {
          if (players[j] != null && (j != i)) {
            if (overlap(players[i], players[j])) {
              if (players[i].isImpostor()) {
                players[j].unalive();
              }
              else if (players[j].isImpostor()) {
                players[i].unalive();
              }
            }
          }
        }
        players[i].draw();
      }
    }
  }
  
  /**
   * This method is run whenever the user presses a button.
   * If the user presses a then a new Amogus character is 
   * added to the players array. If the next free spot in 
   * the players array is the impostorIndex then an imposter
   * is created.
   */
  public static void keyPressed() {
    System.out.println("you pressed a key");
    // generate color and location parameters for amogus
    int randX = Utility.randGen.nextInt(1201);
    int randY = Utility.randGen.nextInt(801);
    int randColor = 1 + Utility.randGen.nextInt(3);
    
    // check if user pressed 'a'
    if (Utility.key() == 'a') {
      System.out.println("you pressed a");
      for (int i = 0; i < NUM_PLAYERS; i++) {
        if (players[i] == null) {
          // if we are at impostorIndex create an impostor
          if (i == impostorIndex) {
            players[i] = new Amogus(randColor, randX, randY, true);
            break;
          }
          players[i] = new Amogus(randColor, randX, randY, false);
          break;
        }
      }
    }
  }
  
  /**
   * This method takes an Amogus object as parameter. It finds
   * the width and height of the Amogus which is used to help
   * determine the hitbox. It also finds the location of the 
   * center of the amogus and the location of the cursor. If
   * the cursor is inside the hit box of the Amogus, then true
   * is returned. Otherwise, false is returned.
   * 
   * 
   * @param amogus      An object of type Amogus from the players array.
   * @return true if the cursor is inside the hitbox of amogus
   */
  public static boolean isMouseOver(Amogus amogus) {
    int width = amogus.image().width;
    int height = amogus.image().height;
    double amogusX = amogus.getX();
    double amogusY = amogus.getY();
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();
    
    // checks if mouse is within sprite
    if (mouseX > amogusX - (width/2) && mouseX < amogusX + (width/2) 
        && mouseY > amogusY - (height/2) && mouseY < amogusY + (height/2)) {
      return true;
    }
    return false;
  }
  
  /**
   * This method is called whenever the user left clicks.
   * If the cursor is over a players when this method runs, 
   * the amogus starts being dragged by the user.
   */
  public static void mousePressed() {
    for (int i = 0; i < NUM_PLAYERS; i++) {
      if (players[i] != null) {
        if (isMouseOver(players[i])) {
          players[i].startDragging();
        }
      }
    }
  }
  
  /**
   * This method executes if the user releases the left click.
   * The method loops through every amogus object in the players
   * array and stops dragging them.
   */
  public static void mouseReleased() {
    for (int i = 0; i < NUM_PLAYERS; i++) {
      if (players[i] != null) {
          players[i].stopDragging();
      }
    }
  }
  
  /**
   * This method checks if the hitboxes of two amoguses are overlapping.
   * First it finds the top leftmost point and the bottom rightmost point
   * for each amogus' hitbox. Then it uses these points to check overlap 
   * conditions. 
   * 
   * 
   * @param amogusOne         An object of type Amogus from the players array.
   * 
   * @param amogusTwo         Another object of type AMogus from the players
   *                          array.
   *                          
   * @return true if the two amoguses are overlapping and false otherwise.
   */
  public static boolean overlap(Amogus amogusOne, Amogus amogusTwo) {
    // width and height for our sprite
    int width = amogusOne.image().width;
    int height = amogusOne.image().height;
    
    // finds the top left and bottom right point of rectangle for each amogus
    double[] amogusOneL = {amogusOne.getX() - (width/2), amogusOne.getY() - (height/2)};
    double[] amogusOneR = {amogusOne.getX() + (width/2), amogusOne.getY() + (height/2)};
    double[] amogusTwoL = {amogusTwo.getX() - (width/2), amogusTwo.getY() - (height/2)};
    double[] amogusTwoR = {amogusTwo.getX() + (width/2), amogusTwo.getY() + (height/2)};
    
    // checks conditions for overlap
    if (amogusOneL[0] > amogusTwoR[0] || amogusOneR[0] < amogusTwoL[0]) {
      return false;
    }
    else if (amogusOneL[1] > amogusTwoR[1] || amogusOneR[1] < amogusTwoL[1]) {
      return false;
    }
    return true;
  }
  
  /**
   * main method to run this SpaceStation class.
   * 
   * @param args list of input args if any
   */
  public static void main(String[] args) {
    Utility.runApplication(); // starts the application
  }

}
