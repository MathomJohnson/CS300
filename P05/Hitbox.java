import processing.core.PApplet;

/**
 * An instantiable class maintains data about Hitboxes used
 * for different objects in the Froggie Feeding Frenzie game.
 * Hitboxes in this application are rectangles.
 * @author Michelle & <YOUR NAME(S) HERE>
 */
public class Hitbox {
  /**the 2D coordinates of the center of this hitbox [x,y]*/
  private float[] coordinates;
  /**the width of the box*/
  private float width; 
  /**the height of the box*/
  private float height;
  /**the PApplet that the hitbox can draw on*/
  private static PApplet processing; 
  
  /**
   * Creates a new Hitbox object based on the given parameters.
   * @param x, the x-coordinate of the center of the hitbox
   * @param y, the y-coordinate of the center of the hitbox
   * @param width, the width of the hitbox
   * @param height, the height of the hitbox
   * @throws IllegalStateException if processing is null 
   * @author Michelle
   */
  public Hitbox(float x, float y,  float width, float height) {
    if(Hitbox.processing == null)
      throw new IllegalStateException("Processing is null. setProcessing() must be called before "
          + "creating any Hitbox objects.");
    this.coordinates = new float[] {x,y};
    this.width = width;
    this.height = height;
  }
  
  /**
   * Changes the coordinates of the center of this Hitbox
   * @param x, the new x-coordinate of the Hitbox's center
   * @param y, the new y-coordinate of the Hitbox's center
   * @author Michelle
   */
  public void setPosition(float x, float y) {
    this.coordinates[0] = x;
    this.coordinates[1] = y;
  }
  
  /**
   * Detects if this Hitbox and another collide by overlapping.
   * @param other, the Hitbox to check for if it collides with this one
   * @return true if the 2 Hitboxes overlap, false otherwise
   * @author [YOUR NAME(S) HERE]
   */
  public boolean doesCollide(Hitbox other) {
    //TODO implement this method
	float[] l1 = new float[] {this.coordinates[0] - (this.width/2), this.coordinates[1] - (this.height/2)};
	float[] l2 = new float[] {other.coordinates[0] - (other.width/2), other.coordinates[1] - (other.height/2)};
	float[] r1 = new float[] {this.coordinates[0] + (this.width/2), this.coordinates[1] + (this.height/2)};
	float[] r2 = new float[] {other.coordinates[0] + (other.width/2), other.coordinates[1] + (other.height/2)};

    // if rectangle has area 0, no overlap
    if (l1[0] == r1[0] || l1[1] == r1[1] || r2[0] == l2[0] || l2[1] == r2[1]) {
      return false;
    }
    
    // If one rectangle is on left side of other 
    if (l1[0] > r2[0] || l2[0] > r1[0]) {
        return false;
    }

    // If one rectangle is above other 
    if (r1[1] < l2[1] || r2[1] < l1[1]) {
        return false;
    }

    return true;
  }
  
  /**
   * Sets the processing for all Hitboxes
   * @param processing, the instance of a PApplet to draw onto
   * @author Michelle
   */
  public static void setProcessing(PApplet processing) {Hitbox.processing = processing;}
  
  /**
   * Change both the width and height of this Hitbox
   * @param width, the new width for the Hitbox
   * @param height, the new height for the Hitbox
   * @author Michelle
   */
  public void changeDimensions(float width, float height) {
    this.height = height;
    this.width = width;
  }

  /**
   * Draws the Hitbox to the screen. Provided solely for visualization and debugging purposes.
   * @author Michelle
   */
  public void visualizeHitbox() {
    processing.noFill(); //make the inside of the rectangle clear
    processing.strokeWeight(3); //make the lines thicker
    //draw a rectangle to the screen
    processing.rect(coordinates[0],coordinates[1],width, height); 
  }
}