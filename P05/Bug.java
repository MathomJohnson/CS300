import java.io.File;

public class Bug extends GameActor{
 /** 
  * how many points this bug gives for being eaten
  */ 
  private int points;
  
  /**
   * path to the image used for bugs, all bugs use the same image
   */
  private static final String IMG_PATH = "images"+File.separator+"bug.png";
  
  /**
   * Creates a new Bug object with the provided information.
   * @param x - the x-coordinate for the center of this bug
   * @param y - the y-coordinate for the center of this bug
   * @param points - the number of points the Bug is worth
   */
  public Bug(float x, float y, int points) {
    super(x,y,IMG_PATH);
    this.points = points;
  }
  
  /**
   * Gets how many points this Bug is worth
   * @return the number of points this Bug is worth
   */
  public int getPoints() {
    return points; 
  }
  
  /**
   * Determines whether or not this bug has been eaten by the Frog.
   * @param f - the frog that has possibly eaten this bug
   * @return true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, 
   * false otherwise note this method should also return false if the tongue is inactive
   */
  public boolean isEatenBy(Frog f) {
	System.out.println("---------------------");
	System.out.println(this.getHitbox().doesCollide(f.getTongueHitbox()));
    if(this.getHitbox().doesCollide(f.getTongueHitbox())) {
      return true;
    }
    return false; //default return statement 
  }
}
