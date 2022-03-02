import javax.swing.SwingUtilities;

import mickel.io.Key;


/** Driver code to instantiate the GUI for this application.
 */
public class BouncingBalls
{

	/** The starting point for the Java Virtual Machine when
	 *  this program is executed.
	 *
	 * @param args		Unused array of input parameters
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				BouncingBallsGUI the = new BouncingBallsGUI();
			}
		});
	}
	
	

}
