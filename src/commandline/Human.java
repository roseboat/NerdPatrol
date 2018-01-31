package commandline;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * The human class is a child of the Player class and is used to model the interaction of the human player
 * with the top trumps game.
 * */
public final class Human extends Player {

	/**
	 * Constructor of Human object. 
	 * 
	 * @param name, name of player
	 * @param playerDeck, the deck given to the player
	 * 
	 * @see Player constructor
	 * */
	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}
	
	/**
	 * The user's card is printed to the console, and the user is requested to input a number 
	 * corresponding the position of the category within the list. This method does not handle user input,
	 * only the information is shown to the human player.
	 * */
	public void promptUser() {	
		StringBuilder humanTurn = new StringBuilder(name + ", please choose your value!\r\n");
		humanTurn.append(heldCard.cardToString());
		humanTurn.append("(Press 1, 2, 3, 4 or 5)");
		System.out.println(humanTurn);
	}

	/**
	 * The user is able to make their choice based on the card that is presented to them. The input they select
	 * corresponds to the category position
	 * 
	 * @throws InputMismatchException, if the user types a string instead of a number
	 * @return index, the index of the chosen category in the cardValues array.
	 * */
	public int chooseCategory() {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		int choice = 0;
		// EEEEEEEEEEEEEEEEEEEEEEEDDDDDDDDDDDDDDDIT THIS, recursive calls
		for (;;) {
			
			try{
				choice = sc.nextInt();
				if (choice > 0 && choice < 6) {
					System.out.println(getName() + " has chosen " + heldCard.getSelectedCategory(choice - 1));
					index = choice -1;
					setChosenCat(heldCard.getCardValues()[index]);
					return index;
				}
				else {
				System.out.println("Please enter a number between 1 and 5");
				chooseCategory();
				return index;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter a number");
				chooseCategory();
				return index;
			}
		}
	}
}

