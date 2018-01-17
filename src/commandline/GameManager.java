package commandline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameManager {

	private Player p1;
	private Player p2;
	private int numPlayers;
	private Deck deck;
	private ArrayList<Player> players;

	
	// DANTE SORT THIS OUT
	public GameManager(int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
		Deck[] cards = deck.advancedSplit(this.numPlayers);
		players.add(new Human("bob", cards[0]));
		for (int i = 1; i < cards.length; i++) {
			players.add(new Computer("Computer " + i, cards[i]));
		}
		Collections.shuffle(players);
	}

	public void checkDecks() {
		for (Player each : players)
			if (each.playerDeck.getDeckSize() < 1) {
				players.remove(each);
			}
	}
	// CALVIN GOES HERE
	public void decideWinner(int index) {

		p1.topCard.setSelectedValue(5);
		p2.topCard.setSelectedValue(39);

		Deck winnerPile = new Deck();

		Card[] c = new Card[] { p1.topCard, p2.topCard };
		int winIndex = -1;
		Card bigCard;
		for (int i = 1; i < c.length; i++) {
			if (c[i] > c[i - 1]) {

			}
		}

	}
	
	// CALVIN WINNERWINSCARDS / LOSERLOSESCARDS
	
	
}
