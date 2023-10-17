package Wordle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Wordle_English {

	// Length of board = number of guesses
	int length = 6;
	
	// Width of board = number of letters in mystery word
	int width = 5;
	
	// Mystery word to guess
	char[] answer;
	
	// Game board of guesses
	JTextField[][] user_guesses = new JTextField[length][width];
	
	/* At bottom of screen, shows user which letters have been
	 * already guessed incorrectly */
	HashMap<Character, JButton> letter_buttons = new HashMap<>();
	
	// Enter button user clicks after they type a guess
	JButton enter;
	
	/* Row in user_guesses, to keep track of which guess to validate,
	 * increments after each time a guess is made */
	int curr_row = 0;
	
	/* Keeps track of which letters have already been accounted for,
	 * which allows us to account for the scenario where there are 
	 * multiple occurrences of a yellow letter in guess, but not all
	 * of them should be yellow */
	HashMap<Character, Integer> letters_count = new HashMap();

	
	
	/* Our constructor which takes takes in a String, sets it to the
	 * answer word and sets up the GUI / programs the enter button */
	public Wordle_English(String answer) {
		this.answer = answer.toCharArray();
		set_up_main_window();
		enter.addActionListener(e -> check_guess(get_guess(curr_row)));
	}
	
	
	
	/* Function which validates a user's guess each time the enter
	 * button is clicked, setting the appropriate color of the
	 * JTextFields in user_guesses to green, yellow, or red */
	void check_guess(char[] guess) {
		
		// update letters_count w/ letters in answer and their counts
		// should maybe be separate function??
		for (int i = 0; i < width; i++) {
			Character key = (Character) answer[i];
			Integer val = letters_count.get(key);
			if (val == null) letters_count.put(key, 1);
			else letters_count.replace(key, val + 1);
		}
		
		// Go through each letter in curr_row of user_guesses - CHANGE?
		for (int col = 0; col < width; col++) {
			char curr_letter = user_guesses[curr_row][col].getText().charAt(0); // NULL POINTER?
			Character key = (Character) curr_letter;
			Integer val = letters_count.get(key);	
			
			// Case 1 - the letter should be green
			if (answer[col] == curr_letter && val > 0) {
				user_guesses[curr_row][col].setBackground(Color.GREEN);
				letters_count.replace(key, val.intValue() - 1);
			}
			
			// Case 2 - the letter should be yellow
			else if (val != null && val > 0) {
				user_guesses[curr_row][col].setBackground(Color.YELLOW);
				letters_count.replace(key, val.intValue() - 1);
			}
			
			/* Case 3 - the letter should be dark gray, letter_buttons
			 *  should be red */
			else {
				user_guesses[curr_row][col].setBackground(Color.DARK_GRAY);
				letter_buttons.get(curr_letter).setForeground(Color.RED);
			}
		}
		
		/* Increment curr_row to move onto next row of guesses matrix
		 * (AKA next guess) */
		curr_row++;
	}
	
	
	
	/* Function which returns the guess we want to validate
	 * based on the current row */
	
	char[] get_guess(int curr_row) { 
		char[] guess = new char[width];
		for(int i = 0; i < width; i++) {
			guess[i] = user_guesses[curr_row][i].getText().charAt(0);
		}
		return guess;
	}
	
	
	
	/* Function which sets up the GUI display, organizes everything
	 * in a JFrame of different components, and sets it to visible */
	void set_up_main_window() {
		
		JFrame main = new JFrame("Wordle");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(500, 600);
		main.setLayout(new BorderLayout());

		JPanel gameboard = new JPanel();
		gameboard.setLayout(new GridLayout(length, width));
		main.add(gameboard, BorderLayout.CENTER);

		enter = new JButton("ENTER");
		main.add(enter, BorderLayout.PAGE_START);

		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				user_guesses[i][j] = new JTextField();
				gameboard.add(user_guesses[i][j]);
				user_guesses[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}


		// Populate the keyboard array with characters A - Z
		for (char c = 'A'; c <= 'Z'; c++) {
			Character letter = c;
			letter_buttons.put(letter, new JButton(letter.toString()));
		}

		// Make a panel and add each key to it, then put it in main BorderLayout frame
		JPanel keyboard = new JPanel(new GridLayout());
		main.add(keyboard, BorderLayout.PAGE_END);
		for (Character letter: letter_buttons.keySet()) {
			keyboard.add(letter_buttons.get(letter));
		}

		// Set everything as visible
		gameboard.setVisible(true);
		main.setVisible(true);
	}
	
}
