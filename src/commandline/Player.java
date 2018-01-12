package commandline;
public class Player {

	private Deck playerDeck;
	private String name;
	
	public Card drawCard() {
		// player draws card whilst there are still cards in deck
		while (playerDeck.getDeckSize()>0) {
			// top card represented by the index 0 in Deck arraylist
			// top card is removed from Deck
			Card topCard= playerDeck[0];
			playerDeck.remove(0);
			
			// returns the top card of player's deck
			return topCard;
		}
	}
	
	public int chooseCategory(Card c) {
		c.getCategories()[i]=c.getStats()[i];
		int stat=0;
		return stat;
	}
	
	public void addToDeck(boolean win, Deck winnerPile) {
		if (win=true) {
			playerDeck.
		}
	}
}
