package commandline;

import java.util.ArrayList;

public abstract class Player {

	private Deck playerDeck;
	private String name;
	
	// not sure how players are assigned decks
	public Player(String name, Deck playerDeck) {
		this.name=name;
		this.playerDeck=playerDeck;
	}
	
	public Card drawCard() {
		
		ArrayList<Card> deckArray=playerDeck.getCards();
		// player draws card whilst there are still cards in deck
		while (playerDeck.getDeckSize()>0) {
			// top card represented by the index 0 in Deck arraylist
			// top card is removed from Deck
			Card topCard= deckArray.get(0);
			deckArray.remove(0);
		
			// returns the top card of player's deck
			return topCard;
		}
		return null;
		// I'll try think of a better solution since dealing with null is just...
	}
	
	public int chooseCategory() {
		int stat=0;
		return stat;
	}
	
	public void addToDeck(boolean win, Deck winnerPile) {
		if (win = true) {
			
			ArrayList<Card> winnerP = winnerPile.getCards();
			playerDeck.addToDeck(winnerP);

		}
	}
	}

