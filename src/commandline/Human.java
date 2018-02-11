package commandline;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The human class is a child of the Player class and is used to model the
 * interaction of the human player with the top trumps game.
 */
public final class Human extends Player {

	/**
	 * Constructor of Human object.
	 * 
	 * @param name,
	 *            name of player
	 * @param playerDeck,
	 *            the deck given to the player
	 * 
	 * @see Player constructor
	 */
	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	/**

	 * Instantiates the mandatory promptUser() method for a Human user.
	 * 
	 * The user's card is printed to the console, and the user is requested to input
	 * a number corresponding the position of the category within the list. This
	 * method does not handle user input, only the information that is shown to the
	 * human player.
	 */
	public void promptUser() {
		StringBuilder humanTurn = new StringBuilder(name + ", please choose your value!\r\n");
		humanTurn.append(heldCard.cardToString()); // Prints the Human user's card to the screen
		humanTurn.append("(Press 1, 2, 3, 4 or 5)"); // Prompts the user for input (a positive integer from 1 to 5)
														// corresponding to the category of their choice
		System.out.println(humanTurn);
	}

	/**
	 * The user is able to make their choice based on the card that is presented to
	 * them. The input they select corresponds to the category position
	 * 
	 * @throws InputMismatchException,
	 *             if the user types a string instead of a number
	 * @return index, the index of the chosen category in the cardValues array.
	 */
	public int chooseCategory() {
	    	StringBuilder humanTurn = new StringBuilder(name + ", please choose your value!\r\n");
	    	humanTurn.append("(Press 1, 2, 3, 4 or 5)"); // Prompts the user for input (a positive integer from 1 to 5)												// corresponding to the category of their choice
		System.out.println(humanTurn);
		Scanner sc = new Scanner(System.in); // Creates a new Scanner object to handle user input
		int index = 0;
		int choice = 0;

		// Sets up an infinite loop which waits for correct user input
		for (;;) {

			try {
				choice = sc.nextInt(); // Scanner object gets user input and tries to place it in an integer variable,
										// choice

				// If the input is correct, the category chosen is printed to the console.
				if (choice > 0 && choice < 6) {
					index = choice - 1; // 1 must be subtracted to get the position of the chosen category in an array
					System.out.println("You have chosen " + heldCard.getSelectedCategory(index));
					setChosenCat(heldCard.getCardValues()[index]); // Gets the actual value that the user has chosen and
																	// sets the Human player's chosenCat to that value
					return index; // Returns the index and leaves the for loop
				}

				// If the user does not input a number between 1 and 5, they are prompted for
				// correct input again
				else {
					System.out.println("Please enter a number between 1 and 5");
					chooseCategory(); // calls the method again
					return index;
				}
			}

			// If the user inputs something other than a number, they are prompted for
			// correct input again
			catch (InputMismatchException e) {
				System.out.println("Please enter a number between 1 and 5");
				chooseCategory();
				return index;
			}
		}
		
	}
}
