import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * @author Michelle Perz
 * WordBoard is the JPanel that implements the players board
 */
public class WordBoard extends JPanel{
	
	private static final long serialVersionUID = -8611666855233090222L;
	private ArrayList<Word> words = new ArrayList<Word>();
	private String charSeq = "";
	
	/**
	 * Empty constructor
	 */
	public WordBoard() {
	}


	/**
	 * @param word pass a word object to add a word
	 * @return true or false if word added is already in the grid. 
	 */
	public boolean addWord(Word word) {
		
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getWord().equals(word.getWord())) {
				return false;
			}
		}
		words.add(word); //Add the word to the ArrayList
		this.add(word); //Add the word to the JPanel
		return true;
	}
	
	
	/**
	 * A trinary method to check if a word is a word
	 
	 * @param x where x is a character that was last clicked
	 * @return -1 if letter sequence is not found
	            0 if letter sequence found is not a word (yet)
	            1 if a full word was found
	 */
	public int checkWord(char x){
		charSeq = charSeq + x;
		int ret = -1;
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getWord().startsWith(charSeq)) {
				ret = 0;
			}
			if (words.get(i).getWord().equals(charSeq)) {
				ret = 1;
			}
		}
		// If we did not find the character sequence, maybe the user wanted to restart
		if (ret == -1 && charSeq.length() > 1) {
			charSeq = "";
			ret = this.checkWord(x);
		}
		return ret;
	}
	
	/**
	 * @return the length of the characters in the stack
	 */
	public int stackLength() {
		System.out.println(charSeq);
		return this.charSeq.length();
	}
	
	/**
	 * A method which strikes through a word in the word bank when it is found in the grid
	 * @param word pass a word object to StrikeThrough
	 */
	public void strikeThrough (Word word) {
		//Find word in array list then set font size or something
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getWord().equals(word.getWord())) {
				word.setWord("<html><strike>" + word.getWord() + "</strike></html>");
			}
		}
	}
	
	/**
	 * @return null if string of characters is not a word in word bank
	 */
	public Word getWordOnStack () {
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getWord().equals(charSeq)) {
				return words.get(i);
			}
		}
		return null;
	}
	
}
