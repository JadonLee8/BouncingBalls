


import mickel.anim.Stage;
import mickel.image.ImageFile;
import mickel.io.Key;
import javax.sound.midi.*;
import mickel.io.Key;
import java.awt.Color;


/** The primary GUI window of the BouncingBalls application.
 */
public class BouncingBallsGUI
{
	// FIELDS
	// ------------------------------------------------------------
	private Stage myStage;			// The base window for the app.
	private boolean currentBgState;
	private ImageFile bg = new ImageFile("background.jpg");


	// CONSTRUCTORS
	// ------------------------------------------------------------
	/** Constructs and initializes each of the components for this
	 *  GUI window.
	 *
	 *  postcondition: The primary GUI window is initialized and
	 *                   its animation cycle is started.
	 *      algorithm: Initialize a new Stage to have a title of
	 *                   "Bouncing Balls", a width of 400, and a
	 *                   height of 400.
	 *                 Set the background of myStage to be a new
	 *                   Color of your choice, preferably with a
	 *                   low alpha value. For example, translucent
	 *                   orange would be new Color(200,100,0,66)
	 *                 Optionally, set the background of myStage
	 *                   to be an image (GIF, JPEG, or PNG)
	 *                   of your choice. For example, the Duke.png
	 *                   file has been provided for you. Construct
	 *                   it using new ImageFile("Duke.png")
	 *                 Set the location of myStage such that it
	 *                   will be centered on a 1280 x 1024 screen.
	 *                   HINT: Use a mathematical expression to
	 *                   automatically calculate the appropriate
	 *                   x and y coordinate the upper left corner
	 *                   of myStage.
	 *                 Invoke this object's addSprites() method.
	 *                 Tell myStage to open its window.
	 *                 Tell myStage to start its animation cycle.
	 */
	public BouncingBallsGUI()
	{
		myStage = new Stage("Bouncing Balls", 1920, 1080);
		myStage.setBackground(new Color(0, 0, 0, 0));
		myStage.setBackground(bg2);
		myStage.setLocation(-10, 0);
		this.addSprites();
		myStage.openWindow();
		myStage.start();
	}


	// METHODS
	// ------------------------------------------------------------
	/** Constructs and adds multiple Ball objects to the Stage.
	 *
	 *  postcondition: Constructs and adds one Ball object for each
	 *                   of the 3 Ball constructors to the Stage.
	 *      algorithm: Declare a Ball variable called b1 and assign
	 *                   to it a new Ball object with no parameters.
	 *                 Declare a Ball variable called b2 and assign
	 *                   to it a new Ball object with 2 specific
	 *                   parameters (width and height of myStage).
	 *                 Declare a Ball variable called b3 and assign
	 *                   to it a new Ball object with 4 specific
	 *                   parameters (x-location, y-location, size,
	 *                   color) of your choice and add it to myStage.
	 *                 Add b1 to myStage.
	 *                 Add b2 to myStage.
	 *                 Add b3 to myStage.
	 */
	private void addSprites()
	{
		Ball b1 = new Ball();
		Ball b2 = new Ball(myStage.getWidth(), myStage.getHeight());
		Ball b3 = new Ball (1280, 1024, 40, Color.GREEN, false);
		Ball b4 = new Ball(1280, 1000, 45, Color.DARK_GRAY, false);
		myStage.add(b1);
		myStage.add(b2);
		myStage.add(b3);
		myStage.add(b4);
		for (int i = 0; i < 300; i++) {
			int randomSize = (int)(Math.abs(Math.random()*300));
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			myStage.add(new Ball(1280, 1024, randomSize, new Color(r, g, b, 255)/*Color.BLACK*/, true));
		}
	}
	private ImageFile bg2 = new ImageFile("unnamed.jpg");
	public void toggleBg(){
		if(currentBgState == true){
			myStage.setBackground(new Color(0, 0, 0, 0));
			myStage.setBackground(bg2);
			currentBgState = false;
		} else {
			myStage.setBackground(new Color(200, 100, 0, 66));
		}
		System.out.println("Toggle Bg");

	}
	

}
