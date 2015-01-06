import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class implements the MouseListener to handle events from each individual letter
 * @author Michelle Perz
 */
public class WSMouseListener implements MouseListener {
	private Letter[][] labels;
	private WordBoard wordBoard;
	private Letter thisLabel;

	/**
	 * Constructor for the MouseListener
	 * @param thisLabel the Letter object we're currently working on
	 * @param labels all the Letter objects in the system
	 * @param wordBoard the current WordBoard we want to modify things on
	 */
	public WSMouseListener(Letter thisLabel, Letter[][] labels, WordBoard wordBoard) {
		this.thisLabel = thisLabel;
		this.labels = labels;
		this.wordBoard = wordBoard;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		thisLabel.toggleClicked(); //Set this as clicked
		switch (wordBoard.checkWord(thisLabel.getValue())) {
		case -1:
			//Run through all the labels, set the background to false
			if (wordBoard.stackLength() > 1) {
				for (int i = 0; i < labels.length; i++) {
					for (int j = 0; j < labels.length; j++) {
						labels[i][j].setClicked(false);
					}
				}
			}
			break;
		case 0:
			///User does nothing
			break;
		case 1:
			//Word solved, make it green and strike through
			Word word = wordBoard.getWordOnStack();
			int[] startPos = word.getPosition();
			int startPosX = startPos[0];
			int startPosY = startPos[1];

			for (int i = 0; i < word.getLength(); i++) {
				switch (word.getDirection()) {
				case 0:
					labels[startPosX][i + startPosY].setSolvedByUser();
					break;
				case 1:
					labels[i + startPosX][startPosY].setSolvedByUser();
					break;
				case 2:
					labels[i + startPosX][i + startPosY].setSolvedByUser();
					break;
				}
			}
			wordBoard.strikeThrough(word);
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
