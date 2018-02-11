package commandline;

import java.util.ArrayList;

/**
 * Player Class models a Player within the game either human or a computer. In
 * both cases similar attributes will be associated with each but differ in the
 * way they choose categories to play within a round. Humans will require input
 * from the user while the computer will always choose the category with the
 * highest value.
 */

public abstract class Player implements Comparable<Player> {

	protected Deck playerDeck;
	protected String name;
	protected Card heldCard;
	private int chosenCat;

	/**
	 * Constructs the player object with the name of the player and the deck that
	 * they will be using. ChosenCat corresponds to the category the player has
	 * chosen, it is initialised to -1 to act as a form of null value.
	 */
	public Player(String name, Deck playerDeck) {
		this.name = name;
		this.playerDeck = playerDeck;
		this.chosenCat = -1;

	}

	/**
	 * Declares that all Player objects must have a chooseCategory() method. This
	 * method will be different depending on the kind of Player instantiated.
	 * 
	 * @return an int representing the index of a chosen category
	 */
	public abstract int chooseCategory();


	/**
	 * Returns the name of the player.
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Calls the drawCard() method in the Deck class which returns the top card of a
	 * deck. It then places this card in the heldCard instance variable.
	 */
	public void drawCard() {

		if (this.playerDeck.getDeckSize() > 0) {
			this.heldCard = this.playerDeck.drawCard();
		}
	}

	/**
	 * CompareTo method implemented by comparable. Compares two player objects based
	 * on the value of the category they have chosen.
	 * 
	 * @param other,
	 *            Player object to be compared against
	 * @return 1, chosen category of this object is larger than the other
	 * @return -1, chosen category of this object is smaller than the other
	 * @return 0, the chosen category of both players are equal
	 */
	public int compareTo(Player other) {

		try {
			if (this.chosenCat > other.chosenCat)
				return 1;
			else if (this.chosenCat < other.chosenCat)
				return -1;
			else
				return 0;
		} catch (NullPointerException e) {
			return -1;
		}

	}
	
	/**
	 * Returns the value of the card held by the player
	 * 
	 * @return heldCard.getSelectedValue, int value of the card held by the player
	 */
	public int getSelectedValue() {
		return heldCard.getSelectedValue();
	}
	
	/**
	 * Sets the value of the card held by the player
	 * 
	 * @param heldCard.setSelectedValue, int value of the card held by the player
	 */
	public void setSelectedValue(int selectedValue)	{
		heldCard.setSelectedValue(selectedValue);
	}

	/**
	 * Returns the card held by the player
	 * 
	 * @return heldCard, Card object held by the player
	 */
	public Card getHeldCard() {
		return heldCard;
	}

	/**
	 * Returns the size of the deck
	 * 
	 * @return size of the deck held by the player
	 */
	public int getDeckSize() {
		return playerDeck.getDeckSize();
	}

	/**
	 * Add cards won by a player to their deck
	 * 
	 * @param winnerPile,
	 *            deck of cards that all players have submitted during a round
	 */
	public void addToDeck(Deck winnerPile) {
		ArrayList<Card> winnerCards = winnerPile.getDeck();
		playerDeck.addCards(winnerCards);
	}

	/**
	 * Returns the value of the chosen category.
	 * 
	 * @return chosenCat, returns the value of the chosen category
	 */
	public int getChosenCat() {
		return chosenCat;
	}

	/**
	 * Sets the value of the chosen category.
	 * 
	 * @param chosenCat,
	 *            integer value of the category a player has chosen
	 */
	public void setChosenCat(int chosenCat) {
		this.chosenCat = chosenCat;
	}

	/**
	 * Adds cards from a round or in the case of draws, many rounds, to the winner's
	 * deck.
	 * 
	 * @param winnerPile,
	 *            an array of card objects
	 */

	public void addToDeck(ArrayList<Card> winnerPile) {
		playerDeck.addCards(winnerPile);

	}

	/**
	 * Method to return a players deck of cards (ie. their entire hand)
	 * 
	 * @return an ArrayList of card objects
	 */

	public ArrayList<Card> getDeck() {
		return playerDeck.getDeck();
	}

	/**
	 * toString method to access the name of the player
	 * 
	 * @return a String with the name of the Player
	 */
	@Override
	public String toString() {
		return name;
	}

}
