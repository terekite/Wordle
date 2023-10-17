package Wordle;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Wordle_Parent {
	
	protected static char[] answer; // the word which user must try to guess
	
	protected static int length = 6; // length of game board (number of guesses per game)
	protected static int width = 5; // width of game board (number of letters in a word)
	
	
	// FOR NOT FIXED DATA MEMBERS, HAVE METHODS WITHIN THE MAIN GAME AND GUI TO PASS
	// THAT VALUE BACK AND FORTH
	protected static JTextField[][] guesses = new JTextField[length][width]; // the game board user sees
	protected static JTextField[][] ghost_guesses = new JTextField[length][width];// an array of boxes which mirrors the board, but isn't displayed to user
	
	private static int num_of_buttons = 26; // number of buttons in on-screen keyboard
	protected static JButton[] keyboard_buttons = new JButton[num_of_buttons]; // 1 JButton for each key - MAKE DICT
	
	public Wordle_Parent(String answer) {
		Wordle_Parent.answer = answer.toCharArray(); // MAKE STRING NOT CHAR ARRAY
	}
	
}
