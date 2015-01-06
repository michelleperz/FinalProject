import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;


/**
 * This is the main class for the Word Search Extravaganza
 * @author Michelle Perz
 * @version 0.25
 */
public class WordSearchE {

	private JFrame frmWordSearchExtravaganza;
	private static int gridSize = 15;
	private Letter[][] labels = new Letter[gridSize][gridSize];

	/**
	 * Launch the application.
	 *
	 * @param args Arguments from command line
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordSearchE window = new WordSearchE();
					window.frmWordSearchExtravaganza.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WordSearchE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO implement WordSearch.txt Reader, pass the array to Word()
		int numWords = 11;
		String[] dictionary = new String[numWords];
		java.io.File file = new java.io.File("Dictionary.txt");
		int count = 0;

		try {
			Scanner input = new Scanner(file);
			while (input.hasNext() && count < numWords) {
				dictionary[count]=input.nextLine();
				count++;
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}

		final Word[] Words = new Word[numWords];

		Border blackBorder = BorderFactory.createLineBorder(Color.black);

		frmWordSearchExtravaganza = new JFrame();
		frmWordSearchExtravaganza.setMinimumSize(new Dimension(800, 600));
		frmWordSearchExtravaganza.setTitle("Word Search Extravaganza");
		frmWordSearchExtravaganza.setSize(new Dimension(1024, 768));
		frmWordSearchExtravaganza.setBounds(100, 100, 450, 300);
		frmWordSearchExtravaganza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final WordBoard wordPanel = new WordBoard();
		frmWordSearchExtravaganza.getContentPane().add(wordPanel, BorderLayout.SOUTH);

		JPanel gridPanel = new JPanel();
		frmWordSearchExtravaganza.getContentPane().add(gridPanel, BorderLayout.CENTER);
		gridPanel.setLayout(new GridLayout(gridSize, gridSize, 0, 0));

		/*
		 * Add each Letter to the grid
		 */
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				labels[i][j] = new Letter();
				labels[i][j].setBorder(blackBorder);
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				labels[i][j].addMouseListener(new WSMouseListener(labels[i][j], labels, wordPanel));
				gridPanel.add(labels[i][j]);
			}
		}

		/*
		 * Add each Word to the grid, overwriting the Letters
		 */
		for (int i = 0; i < numWords; i++) {
			Words[i] = new Word(dictionary);
			Random r = new Random ();
			boolean wordPlaced = false;
			Words[i].setBorder(blackBorder);

			//If we have a duplicate object, find new words
			while (!wordPanel.addWord(Words[i])) {
				Words[i] = new Word(dictionary);
				Words[i].setBorder(blackBorder);
			}

			while(! wordPlaced) {
				int startPosX = 0;
				int startPosY = 0;
				wordPlaced = true;
				/* Reset direction randomly, lessens the chance of unsolvable situations
				 * in case we loop more than once
				 */
				Words[i].setDirection(); 

				switch (Words[i].getDirection()) {
				case 0:
					//direction = Word.LEFTRIGHT;
					startPosY = r.nextInt(gridSize - Words[i].getWord().length());
					startPosX = r.nextInt(gridSize);
					for (int j = 0; j < Words[i].getWord().length(); j++) {
						if (labels[startPosX][j + startPosY].isPartOfWord) {
							while (j > 0) {
								j--;
								labels[startPosX][j + startPosY].isPartOfWord = false;
								labels[startPosX][j + startPosY].setDebugColl();	
							}
							wordPlaced = false;
							break;
						}
						labels[startPosX][j + startPosY].setValue(Words[i].getWord().charAt(j));
						labels[startPosX][j + startPosY].isPartOfWord = true;
						labels[startPosX][j + startPosY].setDebug();
					}
					break;
				case 1:
					//direction = Word.UPDOWN;
					startPosX = r.nextInt(gridSize - Words[i].getWord().length());
					startPosY = r.nextInt(gridSize);
					for (int j = 0; j < Words[i].getWord().length(); j++) {
						if (labels[j + startPosX][startPosY].isPartOfWord) {
							while (j > 0) {
								j--;
								labels[j + startPosX][startPosY].isPartOfWord = false;
								labels[j + startPosX][startPosY].setDebugColl();	
							}
							wordPlaced = false;
							break;
						}
						labels[j + startPosX][startPosY].setValue(Words[i].getWord().charAt(j));
						labels[j + startPosX][startPosY].isPartOfWord = true;
						labels[j + startPosX][startPosY].setDebug();
					}
					break;
				case 2:
					//direction = Word.DIAGRIGHTDOWN;
					startPosY = r.nextInt(gridSize - Words[i].getWord().length());
					startPosX = r.nextInt(gridSize - Words[i].getWord().length());
					for (int j = 0; j < Words[i].getWord().length(); j++) {
						if (labels[j + startPosX][j + startPosY].isPartOfWord) {
							while (j > 0) {
								j--;
								labels[j + startPosX][j + startPosY].isPartOfWord = false;
								labels[j + startPosX][j + startPosY].setDebugColl();	
							}
							wordPlaced = false;
							break;
						}
						labels[j + startPosX][j + startPosY].setValue(Words[i].getWord().charAt(j));
						labels[j + startPosX][j + startPosY].isPartOfWord = true;
						labels[j + startPosX][j + startPosY].setDebug();
					}
					break;
				}
				Words[i].setPosition(startPosX, startPosY);
			}

			wordPanel.addWord(Words[i]);
		}

		JMenuBar menuBar = new JMenuBar();
		frmWordSearchExtravaganza.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmShowSolution = new JMenuItem("Show Solution");
		mntmShowSolution.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * The Letter object can inspect itself to see if it's part of a word
				 */
				for (int i = 0; i < gridSize; i++) {
					for (int j = 0; j < gridSize; j++) {
						labels[i][j].setSolvedBySystem();
					}
				}
				/*
				 * Strike through all words on the wordPanel
				 */
				for (int i = 0; i < Words.length; i++) {
					wordPanel.strikeThrough(Words[i]);
				}
			}
		});
		mnFile.add(mntmShowSolution);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmDirections = new JMenuItem("Directions");
		mnHelp.add(mntmDirections);
		mntmDirections.addActionListener(new ActionListener()
		{
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "1. Look for the words listed in the word bank \n" + 
						" 2. Click on a letter in a box to begin highlighting \n" + 
						" 3. When you find a complete word, the box will change to green. \n" + 
						" 4. Click File and then Show Solution to stop at any time." );
			}
		});
	}
}
