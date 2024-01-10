import java.util.Random;
public class BouncingBug extends Bug implements Moveable {
  /**
   * the number of pixels to move horizontally and vertically, formatted [dx,dy]
   */
  private int[] speedNums;
  
  /**
   * a random generator to determine the initial directions
   */
  private Random randGen;
  
  /**
   * keeps track if bug is moving left or right
   */
  private boolean goLeft;
  
  /**
   * keeps track if bug is moving up or down
   */
  private boolean goDown;
  
 /**
  *  a constant, number of points ALL BouncingBugs are worth, 100
  */
  private static final int POINTS = 100;
  
  /**
   * Creates a new Bouncing Bug object using the given parameters.
   *  Randomly determines if the bug will initially move left or right. 
   * The same applies to if the bug will move up or down.
   * @param x - the x-coordinate for the center of this BouncingBug
   * @param y - the y-coordinate for the center of this BouncingBug
   * @param dx - the number of pixels to move horizontally
   * @param dy - the number of pixels to move vertically
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, POINTS);
    speedNums = new int[] {dx, dy};
    randGen = new Random();
    
    if (randGen.nextInt(2) == 0) {
    	goLeft = true;
    }
    else {
    	goLeft = false;
    }
    
    if (randGen.nextInt(2) == 0) {
      goDown = true;
    }
    else {
      goDown = false;
    }
  }
  
 /**
  *  Moves this BouncingBug dx pixels left or right (depending on the current horizontal direction) and
  *  dy pixels up or down (depending on the current vertical direction). 
  *  The Bug's Hitbox should also move with the BouncingBug. 
  *  The bug only changes its xy-coordinates if it should move. 
  *  If the center of the Bouncing Bug hits an end of the window it will switch directions.
  *  Ex. The Bug is going down and left and hits the left side of the screen then the Bug will be going down and right.
  */
  public void move() {
	if (!this.shouldMove()) {
	  return;
	}
    
	Hitbox hitbox = this.getHitbox();
	
	float x = this.getX();
	float y = this.getY();
    
    if (goLeft) { // if going left
      if (x - speedNums[0] < 0) {
    	goLeft = false;
      }
      else {
        this.setX(x - speedNums[0]);
        hitbox.setPosition(this.getX() - speedNums[0], this.getY());
      }
    }
    else { // if going right
      if (x + speedNums[0] > 800) {
        goLeft = true;
      }
      else {
        this.setX(x + speedNums[0]);
        hitbox.setPosition(this.getX() + speedNums[0], this.getY());
      }
    }
    
    if (goDown) { // if going down
      if (y + speedNums[1] > 600) {
        goDown = false;
      }
      else {
        this.setY(y + speedNums[1]);
        hitbox.setPosition(this.getX(), this.getY() + speedNums[1]);
      }
    }
    else { // if going up
      if (y - speedNums[1] < 0) {
    	goDown = true;
      }
      else {
        this.setY(y - speedNums[1]);
        hitbox.setPosition(this.getX(), this.getY() - speedNums[1]);
      }
    }
  }
  
  /**
   * Reports if the BouncingBug needs to move.
   * @return true, this Bug ALWAYS moves
   */
  public boolean shouldMove() {
    return true;
  }  
  
}
