package Wordle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Wordle_GUI extends Wordle_Parent {

	public Wordle_GUI(String answer) { 
		
		super(answer);
		//Set up main window

		JFrame main = new JFrame("Wordle");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(500, 600);
		main.setLayout(new BorderLayout());

		JPanel gameboard = new JPanel();
		gameboard.setLayout(new GridLayout(length, width));
		main.add(gameboard, BorderLayout.CENTER);

		JButton enter = new JButton("ENTER");
		main.add(enter, BorderLayout.PAGE_START);

		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				guesses[i][j] = new JTextField();
				gameboard.add(guesses[i][j]);
				guesses[i][j].setBackground(Color.LIGHT_GRAY);
				ghost_guesses[i][j] = new JTextField("GHOST");
			}
		}


		// Populate the keyboard array with characters a - z
		for (char i = 'a', iteration = 0; i <= 'z'; i++, iteration++) {
			Character c = i;
			String name = c.toString();
			keyboard_buttons[iteration] = new JButton(name);
			//MAKE KEYBOARD BUTTONS A DICTIONARY W/ {'A':JBUTTON}
		}

		// Make a panel and add each key to it, then put it in main BorderLayout frame
		JPanel keyboard = new JPanel(new GridLayout());
		main.add(keyboard, BorderLayout.PAGE_END);
		for (JButton key: keyboard_buttons) {
			keyboard.add(key);
		}

		// Set everything as visible & program enter button
		gameboard.setVisible(true);
		main.setVisible(true);
		enter.addActionListener((ActionListener) this);

	}
}
