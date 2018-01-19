package commandline;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Human extends Player {

	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	public void promptUser() {
	    	drawCard(); // USER needs to draw a card before being prompted
		System.out.println("Choose your category!");
		String x = topCard.cardToString();
		System.out.println(x);
		//chooseCategory();
		altChooseCategory();
		
		
	}

	// DANTE HANDLE THE EXCEPTIONS PLEASE
	public int chooseCategory() {

		// reads in user's input
		// user selects the category from a list
		InputStreamReader readInput = new InputStreamReader(System.in);
		Scanner in = new Scanner(readInput);
		int choice = in.nextInt();

		int index = choice - 1;
		// int [] test = topCard.getAllValues();
		// int chosenValue = test[index];

		return index;

	}
	
	public void altChooseCategory() {
	    Scanner sc = new Scanner(new InputStreamReader(System.in));
	    int choice = sc.nextInt();
	    do {
		switch (choice){
		case (1):
		    System.out.println("You have chosen " + topCard.getSelectedCategory(choice - 1));
			break;
		case (2):
		    System.out.println("You have chosen " + topCard.getSelectedCategory(choice - 1));
			break;
		case (3):
		    System.out.println("You have chosen " + topCard.getSelectedCategory(choice - 1));
			break;
		case (4):
		    System.out.println("You have chosen " + topCard.getSelectedCategory(choice - 1));
			break;
		case (5):
		    System.out.println("You have chosen " + topCard.getSelectedCategory(choice - 1));
			break;
		default:
		    System.out.println("The number chosen is not an allowed value. Please repick the category");
		    choice = sc.nextInt();
		}
	    } while (choice < 1 || choice > 5);
	    
	}

	

	

}
	
	