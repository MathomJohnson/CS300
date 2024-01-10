import java.io.File;
import processing.core.PImage;


/**
 * A subclass of Bug that is Movable and moves in a circle. 
 * When drawn to the screen they also have a tint applied to the image
 *
 *@author ojasvini Sharma and Mathom Johnson
 */
public class CirclingBug extends Bug implements Moveable{
  
  /**
   * keeps track of how long the bug has been moving
   */
  private double ticks;
  
  /**
   * the radius of the circle path this bug moves on
   */
  private double radius;
  
  /**
   * the x,y-coordinates for the center of the circle path
   */
  private float[] circleCenter;
  
  /**
   * color used to tint the image when being drawn [Red,Green,Blue]
   */
  private int[] tintColor;
  
  /**
   * constant, number of points ALL CirclingBugs are worth 200
   */
  private static final int POINTS = 200;
  
  /**
   * Creates a new CirclingBug object using the provided parameters.
   *  By default the number of ticks for a new CirclingBug should be 0.0. 
   *  The x,y-coordinates for the initial position of the Bug must be calculated by using the equations given in the write-up for ticks = 0.0.
   *  @param circleX - the x-coordinate for the center of the circle path
   *  @param circleY - the y-coordinate for the center of the circle path
   *  @param radius - the radius of this CirclingBug's circle path
   *  @param tintColor - an array containing the Red,Green, and Blue values for the tint color You can assume that this array is ALWAYS length 3.
   */
  public CirclingBug(float circleX, float circleY, float radius, int[] tintColor) {
	super(circleX + radius, circleY, POINTS);
    this.circleCenter = new float[] {circleX, circleY};
    this.ticks = 0.0;
    this.radius = radius;
    this.tintColor = tintColor;
  }
  
  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. 
   * After the image is drawn to the screen the tinting effect will need to done undone by tinting it again with white. (255, 255, 255)
   * @overrides draw in class GameActor
   */
  public void draw(){
	processing.tint(tintColor[0], tintColor[1], tintColor[2]);
	PImage img = processing.loadImage("images"+File.separator+"bug.png");
    processing.image(img, this.getX(), this.getY());
	processing.tint(tintColor[0], tintColor[1], tintColor[2]);
    processing.tint(255, 255, 255);
  }
  
  /**
   * Moves this CirclingBug to the next position on its circular path. (See write-up for more details on how to calculate this path.) 
   * The Hitbox should move with the CirclingBug. The bug only changes its xy-coordinates if it should move. 
   * Ticks will advance by 0.05 whenever this method is called.
   * 
   */
  public void move() {
    if (!this.shouldMove()) {
    	return;
    }
    
    float newX = (float) (radius * Math.cos(ticks) + circleCenter[0]);
    float newY = (float) (radius * Math.sin(ticks) + circleCenter[1]);
    
    this.setX(newX);
    this.setY(newY);
    
    this.getHitbox().setPosition(newX, newY);
    
    ticks += 0.05;
  }
  
  /**
   * Reports if the CirclingBug needs to move.
   * @return true, this bug ALWAYS moves
   */
  public boolean shouldMove() {
    return true;
  }
  
}
