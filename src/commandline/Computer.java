package commandline;

/**
 * Computer class extends the Player class and models the behaviour of a
 * computer player.
 */

public final class Computer extends Player {

	/**
	 * Computer class inherits the same constructor as the super Player class.
	 * 
	 * @param name, the name of the Computer player
	 * @param playerDeck, the deck given to the Computer player
	 * 
	 * @see Player constructor
	 */
	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	/**
	 * Instantiates the mandatory promptUser() method for a Computer user. Prints
	 * the Computer player's card to the Command Line.
	 */
	public void promptUser() {

//		StringBuilder computerTurn = new StringBuilder(name + "'s top card:\r\n");
//		computerTurn.append(heldCard.cardToString()); // gets a String representation of the Computer user's card
//		System.out.println(computerTurn);
	}

	/**
	 * Instantiates the mandatory chooseCategory() method for a Computer player. The
	 * computer player will always choose the category with the highest value.
	 * 
	 * @return an int value that corresponds to the index of an array of categories.
	 */
	public int chooseCategory() {

		int[] values = heldCard.getCardValues(); // Gets the values of all the categories on the Computer player's held
													// card and places them in an array

		int max = values[0]; // Sets the maximum value to the first value in the array
		int index = 0; // Sets an int named index to the default value of 0

		// Loops through all the values on the Computer player's card, comparing one
		// against the other and setting the maximum to the largest in each comparison

		for (int i = 0; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
				index = i; // Sets the index value to the position in the array in which the largest
							// element can be found
			}
		}
		
		 // Prints the Computer player's choice to the command line
		System.out.println(getName() + " has chosen " + heldCard.getSelectedCategory(index));
		System.out.println();

		// Sets the Computer player's chosen category to the maximum value in the values
		// array
		setChosenCat(heldCard.getCardValues()[index]);

		// Returns an integer that represents the Computer's chosen category as a
		// position in an array.
		return index;
	}
}
