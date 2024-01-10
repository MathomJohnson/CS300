import processing.core.PImage;
import processing.core.PApplet;

public class GameActor {
	/**
	*the x,y-coordinates of the center stored as [x,y]
	*/
	private float[] coordinates;

	/**
	*the hitbox associated with this GameActor
	*/
	private Hitbox hitbox;

	/**
	*the image associated with this GameActor
	*/
	protected processing.core.PImage image;

	/**
	*PApplet to use to draw GameActors to the screen
	*/
	protected static processing.core.PApplet processing;
	
	/**
	*Constructor for a new GameActor object by setting the coordinates, loading the image, and creating the hitbox.
	*@param x - the x-coordinate for the center of this object and its hitbox
	*@param y - the y-coordinate for the center of this object and its hitbox
	*@param imgPath - the path to the image that will be loaded in
	*@throws IllegalStateException - with a descriptive message if processing is null
	*/
	public GameActor(float x, float y, String imgPath)  throws IllegalStateException{
		coordinates = new float[2];
		coordinates[0] = x; coordinates[1] = y;
		image = processing.loadImage(imgPath);
		hitbox = new Hitbox(x, y, image.width, image.height);
		
		if (processing == null) {
			throw new IllegalStateException();
		}
	}

	/**
	*Sets the processing for all Hitboxes
	*@param processing - the instance of a PApplet to draw onto
	*/
	public static void setProcessing(processing.core.PApplet processing){
		GameActor.processing = processing;
	}

	/**
	*Getter for the x-coordinate.
	*@return the x-coordinate of center of this GameActor
	*/
	public float getX(){
		return this.coordinates[0];
	}

	/**
	*Getter for the y-coordinate.
	*@return the y-coordinate of center of this GameActor
	*/
	public float getY(){
		return this.coordinates[1];
	}

	/**
	*Setter for the x-coordinate.
	*@param newX - the new x-coordinate for the center of this GameActor
	*/
	public void setX(float newX){
		this.coordinates[0] = newX;
	}

	/**
	*Setter for the y-coordinate.
	*@param newY - the new y-coordinate for the center of this GameActor
	*/
	public void setY(float newY){
		this.coordinates[1] = newY;
	}

	/**
	*Getter for the Hitbox.
	*@return the Hitbox of this GameActor
	*/
	public Hitbox getHitbox(){
		return this.hitbox; 
	}

	/**
	*Moves this GameActors Hitbox to the provided x,y-coordinates
	*@param x - the new x-coordinate for the center of the GameActor's hitbox
	*@param y - the new y-coordinate for the center of the GameActor's hitbox
	*/
	public void moveHitbox(float x,float y){
		this.hitbox.changeDimensions(x, y);
	}
	
	/**
	*Draws the image of the GameActor to the screen. (OPTIONAL)Visualize the Hitbox to help with debugging.
	*/
	public void draw(){
		processing.image(image, coordinates[0], coordinates[1]);
	}

}
