package Wordle;

import java.awt.*;

public class Wordle_Main_Game extends Wordle_Parent implements ActionListener {

	public Wordle_Main_Game() {
		super("hello");
	}

	int loop = 0; // variable that increments each time enter is clicked - CALL THIS ENTERCLICKED

	public void actionPerformed(ActionEvent e) { // When enter is pressed, check each letter of the guess for yellow, green, or gray
		try {

			//Populate guess array
			char[] guess = new char[5];
			for (int i = 0; i < width; i++) {
				guess[i] = guesses[loop][i].getText().charAt(0);
			}

			// Check all letters for green
			for (int i = 0; i < width; i++) {
				if (answer[i] == guess[i]) {
					guesses[loop][i].setBackground(Color.GREEN);
					ghost_guesses[loop][i].setBackground(Color.GREEN);
				}
			}
//TAAAA
//ABABB {A:, B:3}
			// Check all letters not green for yellow
			for (int i = 0; i < width; i++) {

				// Get first non-yellow box
				int x = String.valueOf(answer).indexOf(guess[i]);
				while (x > 0 && ghost_guesses[loop][x].getBackground().equals(Color.YELLOW)) {
					x++;
					if (x == width)
						x = -1; // No more occurrences of char in answer
					else
						x = String.valueOf(answer).indexOf(guess[i], x);
				}
				if (ghost_guesses[loop][i].getBackground().equals(Color.GREEN)
						|| x == -1) { // not sure about the x == -1 parameter - check
					continue;
				}

				// Check all letters not green for yellow
				for (int j = 0; j < width; j++) {
					if (guess[i] == answer[j]) {
						if (ghost_guesses[loop][x].getBackground().equals(Color.GREEN)) continue;
						//TAKE AWAY CONTINUE STATEMENT, USE IF NOT INSTEAD
						guesses[loop][i].setBackground(Color.YELLOW);
						ghost_guesses[loop][x].setBackground(Color.YELLOW); //COUNT DICT {A:1,B:0}
					}
				} // TRY TO COMBINE THIS FOR LOOP WITH THE NEXT ONE

			}

			// All letters not green or yellow must be gray
			for (int i = 0; i < width; i++) {
				if (guesses[loop][i].getBackground().equals(Color.LIGHT_GRAY)) {
					guesses[loop][i].setBackground(Color.DARK_GRAY);

					// All gray letters of guess must be made red on the keyboard as well
					char grayLetter = guesses[loop][i].getText().charAt(0);
					keyboard_buttons[(int) grayLetter - 65].setForeground(Color.RED);
				}
			}

			loop++; // increment to move onto next row of guesses matrix (AKA next guess)

		} catch(Exception ex) {
			System.out.println("You Must Enter A Valid Guess");
			ex.printStackTrace();
		}
	}
}
