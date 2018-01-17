package commandline;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Human extends Player {

	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	public void promptUser() {

		System.out.println("Choose your category!");
		playerDeck.testPrint();
		chooseCategory();
	}

	public void chooseCategory() {

		// reads in user's input
		// user selects the category from a list
		InputStreamReader readInput = new InputStreamReader(System.in);
		Scanner in = new Scanner(readInput);
		int choice = in.nextInt();

		int choiceInt = 0;
		switch (choice) {

		case 1:
			choiceInt = topCard.getCargo();
			break;
		case 2:
			choiceInt = topCard.getfirePower();
			break;
		case 3:
			choiceInt = topCard.getRange();
			break;
		case 4:
			choiceInt = topCard.getSize();
			break;
		case 5:
			choiceInt = topCard.getSpeed();
			break;
		}

		topCard.setSelectedCategory(choiceInt);
	}

}
	
	