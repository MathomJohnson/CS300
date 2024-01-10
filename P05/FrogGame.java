import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; //array list of the gameActors in the game
  private int score; //the player's current score
  private PImage backgroundImg; //the image to use for the background
  private boolean isGameOver; //keeps track if the game is over, is true if the game is over
  private Random randGen; //random number generator
  private static final int BUG_COUNT = 5; //how many bugs should be on the screen at all times
  
  public static void main(String[] args) {
	  PApplet.main("FrogGame");
  }
  
  @Override
  public void settings(){
    //TODO #1 call PApplet's size() method giving it 800 as the width and 600 as the height
	this.size(800, 600);
  }
  
  @Override
  public void setup() {
    //TODO #2 add PApplet method calls from write-up
	this.getSurface().setTitle("Froggie Feeding Frenzie"); // set title of window
	this.imageMode(PApplet.CENTER); // images when drawn will use x,y as their center
	this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their center
	this.focused = true; // window is "active" upon start up
	this.textAlign(PApplet.CENTER); // text written to screen will have center alignment
	this.textSize(30); // text is 30 pt
    //TODO #3 initialize randGen
	randGen = new Random();
    //TODO #4 load in and save the backgroundImg
	backgroundImg = loadImage("images"+File.separator+"background.jpg");
	//backgroundImg.save("images"+File.separator+"background.jpg");
    //TODO #5 initialize gameActors to an empty ArrayList
	gameActors = new ArrayList<GameActor>();
    //TODO #7 set the processing variable for all classes where necessary (update this as needed)
	FrogGame process;
	process = this;
	Hitbox.setProcessing(process);
	Tongue.setProcessing(process);
	GameActor.setProcessing(process);
	gameActors.add(new GameActor(400, 300, "images"+File.separator+"bug.png"));
	gameActors.add(new BouncingBug(500, 300, 5, 1));
	gameActors.add(new CirclingBug(100, 100, 40, new int[] {200, 50, 25}));
	gameActors.add(new Frog(100, 500, 1));
    //TODO #16 call initGame()
    initGame();
  }

  @Override
  public void draw() {
	//TODO #6 call PApplet's image() method to draw the backgroundImg at the center of the screen
	this.image(backgroundImg, 400, 300);
	//TODO #8 draw every GameActor in the ArrayList to the screen
	for (int i = 0; i < gameActors.size(); i++) {
      this.gameActors.get(i).draw();
	  this.gameActors.get(i).getHitbox().visualizeHitbox();
	}
	//TODO #9 update the code you wrote for TODO #8 to have all Movable GameActors move
	for (int i = 0; i < gameActors.size(); i++) {
	  if (gameActors.get(i) instanceof Moveable) {
		Moveable actor = (Moveable) gameActors.get(i);
		actor.move();
	  }
	}
	//TODO #19 run all the game logic checks
    runGameLogicChecks();
	//TODO #14 print "Health: " + frog's health at (80,40) and "Score: " + score at (240,40)
    //     to the screen
    //     (note in the code logic this step to be performed takes place AFTER TODO #19)
    Frog frog = null;
    for (GameActor actor : gameActors) {
      if (actor instanceof Frog) {
        frog = (Frog) actor;
      }
    }
    text("Health: " + frog.getHealth(), 80, 40);
    text("Score: " + score, 240, 40);
    
	//TODO #20 update the code you wrote above to do the following:
	  //(1) if the game is over, do NONE of the above steps. Instead print "GAME OVER" to
    //    the center of the screen.
	  //(2) otherwise do the above steps
  }
  
  private void addNewBug() {
	//TODO #10 implement this method, see below for more details. 
    //This creates a bug of a random type and adds it to the list of GameActors.
      //(1) generate a random number in the range [0,4)
      //(2) generate a random x value in the range [0, windowWidth) for the bug
      //(3) generate a random y value in the range [0, windowHeight - 150) for the bug
      //(4) depending on the value generated in step (1) 
      //    create the following bug and add it to the arraylist
		 // 0 -> a new regular Bug at (x,y) that is worth 25 points
         // 1 -> a new BouncingBug at (x,y) that has a dx of 2 and a dy of 5
         // 2 -> a new CirclingBug at (x,y) with a radius of 25 and a random set of RGB values [0,256)
         // 3 -> a new StrongBug at (x,y) with an initial health of 3	
    int rand = randGen.nextInt(4);
    int xVal = randGen.nextInt(800);
    int yVal = randGen.nextInt(450);
    
//    if (rand == 0) {
//      gameActors.add(new StrongBug(xVal, yVal, 3));
//    }
//    else if (rand == 1) {
//      gameActors.add(new StrongBug(xVal, yVal, 3));
//    }
//    else if (rand == 2) {
//      gameActors.add(new StrongBug(xVal, yVal, 3));
//    }
//    else if (rand == 3) {
//      gameActors.add(new StrongBug(xVal, yVal, 3));
//    }
    
    if (rand == 0) {
      gameActors.add(new Bug(xVal, yVal, 25));
    }
    else if (rand == 1) {
      gameActors.add(new BouncingBug(xVal, yVal, 2, 5));
    }
    else if (rand == 2) {
      gameActors.add(new CirclingBug(xVal, yVal, 25, 
    	  new int[] {randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256)}));
    }
    else if (rand == 3) {
      gameActors.add(new StrongBug(xVal, yVal, 3));
    }
  }

  @Override
  public void mousePressed() {
	 // TODO #11 if mouse is over the Frog call its mousePressed method
	  
    for (GameActor actor : gameActors) {
      if (actor instanceof Frog) {
          Frog frog = (Frog) actor; // Assuming that Frog is a subclass of GameActor
          if (frog.isMouseOver()) {
              frog.mousePressed();
          }
      }
  }
  }
  
  @Override
  public void mouseReleased() {
	  //TODO #12 call the Frog's mouseReleased method
	   for(GameActor actor: gameActors) {
      if(actor instanceof Frog) {
        Frog frog = (Frog) actor; 
        frog.mouseReleased();
      }
    }
  }
  
  @Override
  public void keyPressed() {
    // TODO #13 if the key is a space, have the frog starts attacking
    if(key == ' ') {
      for(GameActor actor: gameActors) {
        if(actor instanceof Frog) {
          Frog frog = (Frog) actor; 
          frog.startAttack();
        }
      }
    }
    // TODO #17 if the key is a lowercase 'r', reset the game to its initial state
    if(key == 'r') {
      initGame();
    }
  }

  public void initGame() {
	 // TODO #15 implement this method, see below for more details. This methods sets the game to
    // its initial state before playing.
    // (1) set the score to 0
    score = 0;
    // (2) make the game NOT over
    isGameOver = false;
    // (3) clear out the arraylist
    gameActors.clear();
    // (4) create and add Frog with 100 health to the list. Its x value should be half the
    // width of the screen. Its y value should be the height of the screen minus 100
    Frog frog = new Frog(width/2, height - 100, 100);
    gameActors.add(frog);
    frog.draw();
    // (5) add new bugs (of random varieties) to the list UP TO the BUG_COUNT
	for(int i=0; i< BUG_COUNT; i++){
		addNewBug();
	}
  }
  
  private void runGameLogicChecks() {
	//TODO #18 implement this method, see below for details. This method runs all nessisary 
	//game logic checks. Feel free to decompose it into smaller helper methods.
	    //(1) if the Frog's tongue hits the edge of the screen, then it stops attacking
	Frog frog = null;
	for(int i=0; i< gameActors.size(); i++) {
      GameActor actor = gameActors.get(i);
      if(actor instanceof Frog) {
        frog = (Frog) actor;  
        if(frog.tongueHitBoundary()) {
          frog.stopAttack();
        }
      }
    }
		  //(2) Check every bug to see if it has been hit by the Frog.
		    //(a) if a non-StrongBug is hit do the following
			    //(a1) stop the frog's attack
		      //(a2) remove it from the game
			    //(a3) update the score
			    //(a4) add a new bug (of a random variety) to the game
		    //(b) of a StrongBug is hit do the following
		      //(b1) stop the frog's attack
			    //(b2) the StrongBug takes damage and loses health
			    //(b3) if the StrongBug is dead do steps a1 - a4
	 for (int i = gameActors.size() - 1; i >= 0; i--) {
       GameActor actor = gameActors.get(i);
       try {
       if (actor instanceof StrongBug) {
           StrongBug strongBug = (StrongBug) actor;
           System.out.println("theres a strong bug");
           if (strongBug.isEatenBy(frog)) {
        	 System.out.println("strong bug eaten");
             frog.stopAttack(); 
             if (strongBug.isDead()) {
               gameActors.remove(i);
               score += strongBug.getPoints();
               addNewBug();
             }
           }
         }
       } catch (IllegalStateException e) {
    	   break;
       }
       if (actor instanceof Bug && !(actor instanceof StrongBug)) { 
         Bug bug = (Bug) actor;
         System.out.println("theres a normal bug");
         try {
           if (bug.isEatenBy(frog)) {
             System.out.println("-------------------------------------------------");
             System.out.println("normal bug eaten");
             frog.stopAttack();
             gameActors.remove(i);
             score += bug.getPoints();
             addNewBug();
           }
         } catch (IllegalStateException e) {
           break;
         }
       } 
     }
  
		  //(3) check if the frog hits any of the bugs 
		    //(a) if it hit any of the bugs it takes damage and loses health 
		    //    NOTE: it can be hit my multiple bugs at the same time loses health for each. 
		    //      Ex. is hit by 2 different bugs simultanously then should take 2 damage.
		    //(b) if the frog is dead then update the game so it is over
   for (int i = 0; i < gameActors.size(); i++) {
	 GameActor actor = gameActors.get(i);
	 if (actor instanceof Bug) {
	   Bug bug = (Bug) actor;
	   if (frog.isHitBy(bug)) {
		 frog.loseHealth();
		 if (frog.isDead()) {
		   initGame();
		 }
	   }
	 }
   }
  
 }
}