import java.util.Random;

import javax.swing.JLabel;

public class Word extends JLabel {
	
	private static final long serialVersionUID = -1066070645209522324L;
	private int dictSize = 12;
	private String myWord = "";
	private int direction = 0;
	private int[] startPos = new int[2];
	
	/**
	 * How many directions are implemented (currently 3)
	 */
	public static int implementedDirections = 3;
	/**
	 * Integer representation if the direction is Left to Right
	 */
	public static int LEFTRIGHT = 0;
	/**
	 * Integer representation if the direction is Up to Down
	 */
	public static int UPDOWN = 1;
	/**
	 * Integer representation if the direction is Diagonal to the Right and Down
	 */
	public static int DIAGRIGHTDOWN = 2;
	
	/**
	 * Constructor which accepts a dictionary
	 * @param myDictionary Array of words
	 */
	public Word(String[] myDictionary) {
		this.dictSize = myDictionary.length;
		this.setWord(myDictionary[new Random().nextInt(dictSize)]);
		this.setDirection();
	}

	/**
	 * Sets the x-y position of the Letter on the GridPanel
	 * @param x coordinate of the Letter
	 * @param y coordinate of the Letter
	 */
	public void setPosition(int x, int y) {
		this.startPos[0] = x;
		this.startPos[1] = y;
	}
	
	/**
	 * Get the starting position of the Word on the GridPanel
	 * @return Array with x and y value
	 */
	public int[] getPosition() {
		return startPos;
	}
	
	/**
	 * Returns a String representation of the Word
	 * @return the string representation of the word
	 */
	public String getWord() {
		return myWord;
	}

	/**
	 * Set the word for this Word object, the string will be converted to uppercase
	 * @param str Pass a valid String object
	 */
	public void setWord(String str) {
		this.myWord = str.toUpperCase();
		this.setText(myWord);
	}

	
	/**
	 * See the Word static variables for explanation on the directions
	 * @return Integer representing the direction of the word in the grid
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Method which sets the direction of the word to a specified value
	 * @param direction representation of the direction to force the direction of this Word
	 */
	public void setDirection(int direction) {
		this.direction = direction % implementedDirections;
	}

	/**
	 * Method which sets the direction of the word randomly, overload with int to force a direction
	 */
	public void setDirection() {
		this.direction = new Random().nextInt(implementedDirections);
	}

	/**
	 * Shortcut to get the length of the String representation of the Word
	 * @return The length of the word in the array
	 */
	public int getLength() {
		return this.myWord.length();
	}
}
