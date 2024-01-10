import java.io.File;

/** An instantiable class maintains data about a Frog in the Froggie Feeding Frenzie game. 
 *   They an be drawn to the screen, dragged around by the mouse, and attack Bugs with its Tongue.
 * @author Mathom Johnson and Ojasvini Sharma
 */
public class Frog extends GameActor implements Moveable {
    /**
    *how much health the frog has
    */
    private int health;

    /**
    *the tongue belonging to this frog
    */
    private Tongue tongue;
    
    /**
     * keeps track of if the Frog is being dragged by the mouse
     */
    private boolean isDragging;
    
    /**
     * the previous x-coordinate of the mouse
     */
    private float oldMouseX;
    
    /**
     * the previous y-coordinate of the mouse
     */
    private float oldMouseY;
    
    /**
     * path to the image to use for the frog
     */
    private static final String IMG_PATH = "images"+File.separator+"frog.png";
    
    /**
     * Constructor for a new Frog object using the provided parameters. 
     * The Frog is NOT dragging by default.
     * @param x - the x-coordinate for the center of the Frog and starting point of the tongue
     * @param y - the y-coordinate for the center of the Frog and starting point of the tongue
     * @param health - the initial health of this Frog
     * @throws IllegalArgumentException - with a descriptive message if health is less than 1
     */
    public Frog(float x, float y, int health) throws IllegalArgumentException{
      super(x, y,IMG_PATH);
      if(health<1){
          throw new IllegalArgumentException("health is bad");
      }
      this.health = health;  
      this.tongue = new Tongue(x, y);
    }
    
    /**
     * Draws the image of the Frog to the screen. 
     * If the Frog's tongue is active: (1)draw the tongue and (2) extend the tongue by moving 
     * it's x-coordinate to the Frog's x-coordinate and up 2 pixels.
     * @override draw in class GameActor
     */
    public void draw() {
      if(tongue.isActive()) {
          tongue.draw();
          tongue.extend(this.getX(), -2);
        }
      processing.image(image, getX(), getY()); 
    }
    
    /**
     * Moves the Frog by dragging it by the mouse, if it should be dragging. 
     * (See write-up for more details on implementing the dragging functionality.) 
     * The starting point of the tongue and the Hitbox need to move along with the Frog. 
     * If the Frog's tongue is NOT active, move the tongue's endpoint along with the Frog as well. 
     * The Frog only moves if it should move.
     */
    public void move() {
      if(this.shouldMove()) {
        float xMovement = this.getX()+ (processing.mouseX - this.oldMouseX);
        float yMovement = this.getY() + (processing.mouseY - this.oldMouseY);
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
        
        this.setX(xMovement);
        this.setY(yMovement);
        this.getHitbox().setPosition(this.getX(), this.getY());
        tongue.updateStartPoint(this.getX(), this.getY());
        if(!tongue.isActive()) {
          tongue.updateEndPoint(this.getX(), this.getY());
        }
        
      }
    }
    
    /**
     * Determines whether or not this Frog has run into a Bug.
     * @param b - the Bug to check that if it collides with the Frog
     * @return true if the Bug's Hitbox and Frog's Hitbox overlap, false otherwise
     */
    public boolean isHitBy(Bug b) {
      if((this.getHitbox()).doesCollide(b.getHitbox())) {
        return true;
      }
      return false;
    }
    
    /**
     * Gets the Hitbox for this Frog's tongue.
     * @return this Frog's tongue's hitbox
     * @throws IllegalStateException - if the tongue is currently inactive
     */
    public Hitbox getTongueHitbox() {
      if (!tongue.isActive()) {
    	throw new IllegalStateException();
      }
      return tongue.getHitbox();
    }
    
    /**
     * Decreases the health of this Frog by 1.
     */
    public void loseHealth() {
      this.health-=1;
    }
    
    /**
     * Gets the current health of the Frog
     */
    public int getHealth() {
      return this.health;
    }
    
    /**
     * Reports if the Frog needs to move on the screen.
     * @return true if the Frog is being dragged, false otherwise
     */
    public boolean shouldMove() {
     if(this.isDragging) {
       return true;
     }
     return false;
    }
    
    /**
     * Determines if this frog is dead.
     * @return true if this Frog's health is 0 or lower, false otherwise
     */
    public boolean isDead() {
      if(this.health<=0) {
        return true;
      }
      return false;
    }
    
    /**
     * Changes this Frog so it is now being dragged. 
     * This method should only be called externally when 
     * the mouse is over this frog and has been clicked.
     */
    public void mousePressed() {
      if(isMouseOver()){
        isDragging= true;
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
      }
    }
    
    /**
     * Changes this Frog so it is no longer being dragged. 
     * This method should only be called externally when the mouse has been released.
     */
    public void mouseReleased() {
      isDragging = false;
      
    }
    
    /**
     * Determines if the mouse is over the Frog's image
     * @return true, if the mouse is inside the Frog's bounding box of the image, false otherwise
     */
    public boolean isMouseOver() {
      float frogX = this.getX();
      float frogY = this.getY();
      float frogImageWidth = processing.width;
      float frogImageHeight = processing.height;

      return (processing.mouseX >= frogX - frogImageWidth / 2.0f
              && processing.mouseX <= frogX + frogImageWidth / 2.0f
              && processing.mouseY >= frogY - frogImageHeight / 2.0f
              && processing.mouseY <= frogY + frogImageHeight / 2.0f);
    }
    
    /**
     * Starts an attack by resetting the tongue to it's default state and activating the tongue.
     */
    public void startAttack() {
      this.tongue.reset();
      this.tongue.activate();
    }
    
    /**
     * Stops the attack by deactivating the tongue.
     */
    public void stopAttack(){
      this.tongue.deactivate();
    }
    
    /**
     * Reports if this Frog's tongue has hit the top of the screen.
     * @return true if the tongue has hit the top of the screen, false otherwise
     */
    public boolean tongueHitBoundary() {
      if(this.tongue.hitScreenBoundary()){
        return true;
      }
      return false;
    }
}

