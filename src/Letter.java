import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;

/**
 * Date: 5/15/2012
 * Implementation of each Letter represented on the Grid
 * @author Michelle Perz
 */
public class Letter extends JLabel {
	char myLetter;
	
	private static final long serialVersionUID = 851346948984053215L;
	public boolean clicked = false;
	public boolean isPartOfWord = false;
	public boolean isSolved = false;
	public static boolean debug = false;
	

	/**
	 * Constructor which sets sets the background color of the grid to white
	 * and assigns random characters to it
	 */
	public Letter () {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'A');
		this.setValue(c);
		this.setOpaque(true);
		this.setBackground(Color.white);
	}

	/**
	 * @return value of character object
	 */
	public Character getValue () {
		return myLetter;
	}

	/**
	 * Sets the value of a character
	 * @param x where x is a character
	 */
	public void setValue (char x) {
		myLetter = x;
		setText(String.valueOf(myLetter));
	}

	/**
	 * Method which allows the user to click back and forth
	 */
	public void toggleClicked() {
		if (! this.isClicked()) {
			this.setClicked(true);
		} else {
			this.setClicked(false);
		}

	}

	/**
	 * Debug method to change background method to yellow
	 */
	public void setDebug() {
		if (debug)
			this.setBackground(Color.yellow);
	}

	/**
	 * Debug method to change background method to orange
	 */
	public void setDebugColl() {
		if (debug)
			this.setBackground(Color.orange);
	}

	/**
	 * @return True if Letter was clicked on
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * @return True if Word matches word in word bank
	 */
	public boolean isSolved() {
		return isSolved;
	}

	/**
	 * Method which changes background color if user clicks on box
	 * @param clicked if user clicks
	 */
	public void setClicked(boolean clicked) {
		if (!isSolved) {
			if (clicked) {
				this.setBackground(Color.red);
			} else {
				this.setBackground(Color.white);
			}
			this.clicked = clicked;
		} else {
			this.setSolvedByUser();
		}
	}


	/**
	 * If user shows solution, change background color to blue
	 */
	public void setSolvedBySystem() {
		if (isPartOfWord && !isSolved)
			this.setBackground(Color.blue);
	}

	/**
	 * If user finds word, change background color to green
	 */
	public void setSolvedByUser() {
		this.setBackground(Color.green);
		this.isSolved = true;
	}
}
