package commandline;

import java.util.ArrayList;

public abstract class Player {
	
	
	// DANTE LOOK IN HERE MAKE SURE IT'S ALL GOOD
	protected Deck playerDeck;
	private String name;
	protected Card topCard;

	public Player(String name, Deck playerDeck) {
		this.name = name;
		this.playerDeck = playerDeck;
	}

	public void drawCard() {

		if (this.playerDeck.getDeckSize() > 0) {
			// there should never be a situation where a player receives an empty deck
			this.topCard = this.playerDeck.getCards().get(0);
			this.playerDeck.getCards().remove(0);

		}
		// top card represented by the index 0 in Deck arraylist
		// top card is removed from Deck
	}

	public abstract int chooseCategory();

	public void addToDeck(boolean win, Deck winnerPile) {
		if (win = true) {

			ArrayList<Card> winnerP = winnerPile.getCards();
			playerDeck.addCards(winnerP);

		}

	}
}
