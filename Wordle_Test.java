package Wordle;

import java.util.Scanner;

public class Wordle_Test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the secret word: ");
		String answer_word = input.next();
		Wordle_English game = new Wordle_English(answer_word);
	}
}
