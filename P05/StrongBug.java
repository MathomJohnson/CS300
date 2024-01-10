/**
 * A subclass of Bug that is Movable. 
 * These bugs only move when they are not at max health. When hit they become smaller and start moving horizontally across the screen.
 *
 *@author Ojasvini Sharma and Mathom Johnson 
 */
public class StrongBug
extends Bug
implements Moveable{
  /**
   * the max health of this StrongBug
   */
  private final int MAX_HEALTH;
  
  /**
   * he number of points ALL StrongBugs are worth, 500
   */
  private static final int POINTS = 500;
  
  /**
   * the current health of this StringBug, updates when Bug takes damage
   */
  private int currentHealth;
  
  /**
   * Creates a new StrongBug object at full health using the provided parameters.
   * @param x - the x-coordinate for the center of this StrongBug
   * @param y - the y-coordinate for the center of this StrongBug
   * @param health - the max health for this StrongBug
   * @throws IllegalArgumentException - with a descriptive message if health is below one
   */
  public StrongBug(float x, float y, int health) throws IllegalArgumentException{
    super(x, y, POINTS);
    if (health < 1) {
    	throw new IllegalArgumentException("Strong bug's health must be greater than 0!");
    }
    MAX_HEALTH = health;
    currentHealth = MAX_HEALTH;
  }
  
  /**
   * Moves this StrongBug 3 units to the right, wrapping around the screen when the center hits the edge of the window.
   *  The Hitbox should move with the StrongBug. 
   * The x,y-coordinate of only changes if the StrongBug should move.
   */
  public void move() {
	if (!shouldMove()) {
	  return;
	}
    if (this.getX() > 797) {
      this.setX(800 - this.getX());
    }
    else {
      this.setX(this.getX() + 3);
      this.getHitbox().setPosition(this.getX(), this.getY());
    }
  }
  
  /**
   * Reports if this StrongBug is dead.
   * @return true if its currentHealth is 0 or less, false otherwise
   */
  public boolean isDead() {
    if (currentHealth <= 0) {
    	return true;
    }
    return false;
  }
  
  /**
   * Decreases the health of this StrongBug by 1.
   */
  public void loseHealth() {
    currentHealth -= 1;
  }
  
  /**
   * Reports if the StrongBug needs to move.
   * @return true if the bug is NOT at full health, false otherwise
   */
  public boolean shouldMove() {
    if (currentHealth != MAX_HEALTH) {
      return true;
    }
    return false;
  }
  
  /**
   * Determines whether or not this bug has been eaten by the Frog.
   * If the Bug has been hit by the frog:
   * decrease the StrongBug's health
   * resize the image to 75% of its current height and 75% of it's current width
   * change the dimensions of the hitbox to match the new image dimensions.
   * [HINT] PImage resize().
   * @overrides isEatenBy in class Bug
   * @param f - the frog that has possibly eaten this bug
   * @return true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false otherwise
   */
  public boolean isEatenBy(Frog f) {
	if (this.getHitbox().doesCollide(f.getTongueHitbox())) {
	  this.loseHealth(); //loses health
	  System.out.println("strongBug's health == " + currentHealth);
	  image.resize((int) (.75 * image.width), (int) (.75 * image.height));  //decrease the size of the image to 75%
      this.getHitbox().changeDimensions((float)(.75 * image.width), (float)(.75 * image.height)); //change the dimension of the hitbox
      return true;
	}
    return false; //not eaten by frog
  }

}
