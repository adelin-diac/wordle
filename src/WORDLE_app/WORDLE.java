package WORDLE_app;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class WORDLE implements KeyListener, ComponentListener, ActionListener{
	private JFrame mainFrame;
	private int cols = 5;
	private int rows = 6;
	private JLabel[][] rows_columns = new JLabel[rows][cols];
	private int guess;
	private int letter;
	private char[] arrInput;
	private Color orange = new Color(249, 222, 39);
	private Color green = new Color(95, 201, 88);
	private int score;
	private boolean errShown; private boolean gameComp;
	private Dictionary dictionaryObject;
	private JFrame errorMessage;
	private String secretWord;
	private JButton playButton;
	private JFrame correctWord;
	
	public WORDLE() {
		guess = 0; letter = 0; arrInput = new char[5];
		errShown = false; 
		dictionaryObject = new Dictionary("dict.txt");
		secretWord = dictionaryObject.pickRandomWord();
		mainFrame = new JFrame("WORDLE");
		mainFrame.setLayout(new GridLayout(rows+1, cols, 2, 7));
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<cols; j++) {
				rows_columns[i][j] = new JLabel("");
				rows_columns[i][j].setFont(new Font("Arial", Font.BOLD, 50));
				rows_columns[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				rows_columns[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				mainFrame.add(rows_columns[i][j]);
			}
		}
		mainFrame.add(new JLabel());
		mainFrame.add(new JLabel());
		JPanel buttonLabel = new JPanel();
		mainFrame.add(buttonLabel);
		playButton = new JButton("New Word");
		buttonLabel.add(playButton);
		playButton.setFocusable(false);
		playButton.setVisible(false);
		playButton.addActionListener(this);
		
		mainFrame.addKeyListener(this);
		mainFrame.addComponentListener(this);
		mainFrame.setSize(cols*100, (rows+1)*100);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String input = e.getKeyChar() + "";
		if(guess == 6) return;
		if(Character.isLetter(input.charAt(0)) && score != 10) {
			if(errShown == true) {
				errShown = false;
				for(int i = 0; i<letter; i++)
					rows_columns[guess][i].setText("");
				letter = 0;
			}
			if(letter == 5) {return;}
			arrInput[letter] = e.getKeyChar();
			rows_columns[guess][letter++].setText(input.toUpperCase());
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && letter == 5) {
			String guessWord = new String(arrInput).toUpperCase();
			if(dictionaryObject.contains(guessWord)){
				score = 0;
				printColor(guessWord);
				guess++; letter = 0;
				if(score == 10 || guess == 6) {
					playButton.setVisible(true);
					gameComp = true;
					if(score != 10) {
						correctWord = new JFrame();
						correctWord.addComponentListener(this);
						correctWord.setResizable(false);
						correctWord.setSize(200, 100);
						correctWord.setLocation(mainFrame.getLocation().x + cols*50-100, mainFrame.getLocation().y + (rows+1)*50-50);
						correctWord.add(new JLabel("Correct word was: " + secretWord, SwingConstants.CENTER));
						correctWord.setVisible(true);
						correctWord.repaint();
						correctWord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					}
				}
			else {
				errorMessage = new JFrame();
				errorMessage.addComponentListener(this);
				errorMessage.setSize(200, 100);
				errorMessage.setLocation(mainFrame.getLocation().x + cols*50-100, mainFrame.getLocation().y + (rows+1)*50-50);
				errorMessage.add(new JLabel("Invalid Word", SwingConstants.CENTER));
				errorMessage.setVisible(true);
				errorMessage.repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if(letter == 0) return;
			errShown = false;
			rows_columns[guess][--letter].setText("");
		}
	}
	private void printColor(String guessWord) {
		int[] res = checkGuess.verify(secretWord,guessWord);
		for(int ints : res) {score += ints;}
		for(int i = 0; i<res.length; i++) {
			switch(res[i]) {
			case 1:
				rows_columns[guess][i].setBackground(orange);
				rows_columns[guess][i].setOpaque(true);
				break;
			case 2:
				rows_columns[guess][i].setBackground(green);
				rows_columns[guess][i].setOpaque(true);
				break;
			case 0:
				if(score == 10 || gameComp) {
					rows_columns[guess][i].setBackground(null);
					rows_columns[guess][i].setOpaque(true);
				}else {
				rows_columns[guess][i].setBackground(Color.LIGHT_GRAY);
				rows_columns[guess][i].setOpaque(true);
				break;}
			}
		}
	}
	@Override
	public void componentShown(ComponentEvent e) {
		if(e.getSource() == errorMessage) {
			try {TimeUnit.MILLISECONDS.sleep(800);} catch (InterruptedException e1) {}
			errorMessage.setVisible(false);
			errShown = true;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playButton) {
			if(score != 10) {correctWord.setVisible(false);}
			secretWord = dictionaryObject.pickRandomWord();
			clearGame();
			score = 0; guess = 0; letter = 0;
			playButton.setVisible(false);
			gameComp = false;
		}
	}
	public void clearGame() {
		while(guess >= 0) {
			for(int i = 0; i<6; i++) {
				for(int j = 0; j<5; j++)
					rows_columns[i][j].setText("");
			}if(guess == 6) guess--;
			printColor(".....");
			guess--;
		}
	}
	//not implemented
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
}
