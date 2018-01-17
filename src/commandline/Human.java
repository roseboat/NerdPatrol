package commandline;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Human extends Player {

	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	public void promptUser() {

		System.out.println("Choose your category!");
		String x = topCard.cardToString();
		System.out.println(x);
		chooseCategory();
	}

	public int chooseCategory() {

		// reads in user's input
		// user selects the category from a list
		InputStreamReader readInput = new InputStreamReader(System.in);
		Scanner in = new Scanner(readInput);
		choice = in.nextInt();

//		int index = choice - 1;
//		int [] test = topCard.getAllValues();
//		int chosenValue = test[index];
		
		return choice;
		
	}
	

}
	
	