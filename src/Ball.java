


import mickel.anim.Sprite;
import mickel.image.ImageFile;
import mickel.io.Key;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/** Represents a Ball that dynamically bounces around the interior
 *  of a rectangular Stage.
 */
public class Ball extends Sprite
{
	// FIELDS
	// ------------------------------------------------------------
	private int myPosX;		// X-coordinate of this Ball
	private int myPosY;		// Y-coordinate of this Ball

	private int myDirX;		// Horizontal speed of this Ball
	private int myDirY;		// Vertical speed of this Ball

	private int mySize;		// Diameter of this Ball
	private Color myColor;	// Color of this Ball
	private boolean toggleable;
	private boolean soundstatus = true;


	// CONSTRUCTORS
	// ------------------------------------------------------------
	/** Constructs a large, red Ball that is initially located in
	 *  the upper left corner of the screen.
	 *
	 *     algorithm: Assign mySize a value of 100.
	 *                Assign myPosX and myPosY a value of
	 *                  one half mySize plus one.
	 *                Assign myDirX a value of 2.
	 *                Assign myDirY a value of 1.
	 *                Assign myColor a Color value of RED.
	 */
	public Ball() {
		mySize = 100;
		myPosX = (mySize/2) + 1;
		myPosY = (mySize/2) + 1;
		myDirX = 2;
		myDirY = 1;
		myColor = Color.RED;
		toggleable = false;
		soundstatus = true;
		for(int i = 0; i < 20; i++) {
			Color tailColor = new Color(myColor.getRed(), myColor.getBlue(), myColor.getGreen(), 230 - i*11);	
			int slope = (int) (myDirY/myDirX) + myPosY;
			int tailPosX = (int)(myPosX - i*3);
			int tailPosY = (int)(myPosY - (slope/i*3));
			new Ball(tailPosX, tailPosY, mySize, tailColor, toggleable);
		}
	}

	/** Constructs a small, green Ball that is initially centered
	 *  on a specified region of the screen.
	 *
	 *  precondition: width >= 0, height >= 0
	 *     algorithm: Assign mySize a value of 25.
	 *                Assign myPosX a value of half of width
	 *                Assign myPosY a value of half of height
	 *                Assign myDirX a value of -2.
	 *                Assign myDirY a value of 5.
	 *                Assign myColor a Color value of GREEN.
	 *
	 * @param width		Width of the stage
	 * @param height	Height of the stage
	 */
	public Ball(int width, int height) {
		mySize = 25;
		myPosX = width/2;
		myPosY = height/2;
		myDirX = -2;
		myDirY = 5;
		myColor = Color.green;
		toggleable = false;
		soundstatus = true;
		for(int i = 1; i < 20; i++) {
			Color tailColor = new Color(myColor.getRed(), myColor.getBlue(), myColor.getGreen(), 230 - i*11);	
			int slope = (int) (myDirY/myDirX) + myPosY;
			int tailPosX = (int)(myPosX - i*3);
			int tailPosY = (int)(myPosY - (slope/i*3));
			new Ball(tailPosX, tailPosY, mySize, tailColor, toggleable);
		}
	}

	/** Constructs a Ball that initially has the specified
	 *  location, size, and color.
	 *
	 *     algorithm: Assign mySize a value of size.
	 *                Assign myPosX the value of x.
	 *                Assign myPosY the value of y.
	 *                Assign myDirX the value of 3.
	 *                Assign myDirY the value of 3.
	 *                Assign myColor the value of c.
	 *
	 * @param x		The x-coordinate of this Ball's location
	 * @param y		The y-coordinate of this Ball's location
	 * @param size	The diameter of this Ball
	 * @param c		The Color of this Ball
	 */
	public Ball(int x, int y, int size, Color c, boolean t) {
		mySize = size;
		myPosX = x;
		myPosY = y;
		myDirX = 3;
		myDirY = 3;
		myColor = c;
		toggleable = t;
		soundstatus = true;
		for(int i = 0; i < 20; i++) {
			Color tailColor = new Color(myColor.getRed(), myColor.getBlue(), myColor.getGreen(), 230 - i*11);	
			int slope = (int) (myDirY/myDirX) + myPosY;
			int tailPosX = (int)(myPosX - i*3);
			int tailPosY = (int)(myPosY - (slope/i*3));
			new Ball(tailPosX, tailPosY, mySize, tailColor, toggleable);
		}
	}


	// METHODS
	// ------------------------------------------------------------
	/** Performs one frame of movement for this Ball object.
	 *
	 *  precondition:
	 * postcondition: The location of this Ball will be incremented
	 *                by one frame in its current direction of
	 *                movement. If the resulting location lies
	 *                beyond the boundaries of the Stage, this Ball's
	 *                direction will be adjusted accordingly to
	 *                reflect a "bounce".
	 *     algorithm: Increment myPosX by myDirX.
	 *                Increment myPosY by myDirY.
	 *                Declare an int variable w and initialize it
	 *                  with the value of the width of this Ball's
	 *                  Stage.
	 *                Declare an int variable h and initialize it
	 *                  with the value of the height of this Ball's
	 *                  Stage.
	 *                If the left edge of this Ball is less than 0
	 *                  and this Ball is moving left, negate the
	 *                  value of myDirX.
	 *                If the right edge of this Ball is greater than
	 *                  w and this Ball is moving right, negate the
	 *                  value of myDirX.
	 *                If the top edge of this Ball is less than 0
	 *                  and this Ball is moving up, negate the
	 *                  value of myDirY.
	 *                If the bottom edge of this Ball is greater than
	 *                  h and this Ball is moving down, negate the
	 *                  value of myDirY.
	 */
	private final int startSize = mySize;
	public void act()
	{
		boolean grow = false;
		myPosX += myDirX;
		myPosY += myDirY;
		int w = this.getStage().getWidth();
		int h = this.getStage().getHeight();
		if (myPosX < 0 && myDirX < 0) {
			myDirX *= -1;
			playSound();
		}
		if (myPosX > (w-mySize) && myDirX > 0) {
			myDirX *= -1;
			playSound();
		}
		if (myPosY < 0 && myDirY < 0) {
			myDirY *= -1;
			playSound();
		}
		if (myPosY > (h-mySize) && myDirY > 0) {
			myDirY *= -1;
			playSound();
		}
//		if (mySize > startSize) {
//			grow = false;
//			mySize -= 1;
//		} else if (mySize < startSize*.3) {
//			grow = true;
//			mySize += 1;
//		}
//		if (grow = true) {
//			mySize += 1;
//		} else {
//			mySize -= 1;
//		}
	}


	public void playSound() {
		File filePath = new File("OOF.wav");
		InputStream sound;
		if (soundstatus) {
			try {
				if (filePath.exists()) {
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
				
				} else {
					System.out.println("Cannot find file");
				}
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}

	/** Draws a representation of the current state of this Ball
	 *  onto the graphics canvas, g.
	 *
	 *  precondition: g is the "canvas" of the Stage
	 * postcondition: Draws a representation of the current state
	 *                of this Ball onto the graphics canvas, g.
	 *     algorithm: Set the Color of g to be myColor.
	 *                Paint a filled oval onto g that is located at
	 *                  (myPosX, myPosY) with a width and height of
	 *                  mySize.
	 *
	 * @param g	The "canvas" on which this Ball is to be drawn.
	 */
	public void draw(Graphics2D g)
	{

		g.setColor(myColor);
		g.fillOval(myPosX, myPosY, mySize, mySize);
		
	}


	/** Gets the boundaries of this Sprite.
	 *
	 * postcondition: Returns a Shape object that corresponds to the
	 *                characteristics used in the draw() method.
	 *     algorithm: Return a new Ellipse2D.Double object that uses
	 *                  myPosX and myPosY for the x and y parameters
	 *                  and mySize for the width and height parameters.
	 *
	 * @return   The Shape specifying the boundaries of this Sprite
	 */
	public Shape getShape()
	{
		return new Ellipse2D.Double(myPosX, myPosY, mySize, mySize);
	}


	/* The following methods are event-handling methods that respond
	 * to keyboard events and mouse events. You may wish to experiment
	 * with these to add additional "user controls" for Ball objects.
	 */
	private ImageFile bg = new ImageFile("background.jpg");
	private ImageFile bg2 = new ImageFile("unnamed.jpg");
	private ImageFile burntOrange = new ImageFile("burnt-orange.png");
	private ImageFile white = new ImageFile("white.png");
	public boolean followModeOn = false;
	public double speedFactor = .9999999;
	public double increaseFactor = 1.1;
	public void keyPressed (Key k) {
		if (k == Key.C){
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			this.myColor = new Color(r, g, b, 255);
		}
		if (k == Key.S) {
			this.mySize -= 10;
		}
		if (k == Key.B) {
			this.mySize += 10;
		}
		if (k == Key.F){
			followModeOn = true;
		}
		if (k == Key.A){
			followModeOn = false;
		}
		if (k == Key.Q){
			myDirX *= speedFactor;
			myDirY *= speedFactor;
		}
		if (k == Key.W){
			myDirX *= increaseFactor;
			myDirY *= increaseFactor;
		}
		if (k == Key.Y) {
			this.getStage().setBackground(new Color(0, 0, 0, 20));
			this.getStage().setBackground(bg2);
		}
		if (k == Key.U) {
			this.getStage().setBackground(new Color(200, 100, 0, 66));
			this.getStage().setBackground(burntOrange);
		}
		if (k == Key.T) {
			this.getStage().setBackground(white);
			this.getStage().setBackground(new Color(209, 92, 2, 255));
		}
	}
	public void keyReleased(Key k) { /* TODO: Insert code */ }
	private int lastSize;
	public void keyTyped   (Key k) {
		if (k == Key.G && toggleable) {
			lastSize = mySize;
			myColor = new Color(0, 0, 0, 0);
			soundstatus = false;
			mySize = 0;
		}
		if (k == Key.V && toggleable) {
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			myColor = new Color(r, g, b, 255);
			soundstatus = true;
			mySize = lastSize;
		}
	}

	public void mousePressed (int x, int y) { /* TODO: Insert code */ }
	public void mouseReleased(int x, int y) { /* TODO: Insert code */ }
	public void mouseClicked (int x, int y) {
		int randomSize = (int)(Math.abs(Math.random()*300));
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		int randomCoord = (int) (Math.abs(Math.random()*1280));
		this.getStage().add(new Ball(x, y, randomSize, new Color(r, g, b, 255), false));
	}
	public void mouseMoved   (int x, int y) {
		final double aF = 1.3;
		if (followModeOn == true){
			if (x - this.myPosX < 0){
				myDirX -= aF;
			} else {
				myDirX += aF;
			}
			if (y - this.myPosY < 0){
				myDirY -= aF;
			} else {
				myDirY += aF;
			}
		}
	}
	public void mouseDragged (int x, int y) { /* TODO: Insert code */ }
	public void mouseEntered (int x, int y) { /* TODO: Insert code */ }
	public void mouseExited  (int x, int y) { /* TODO: Insert code */ }

}
