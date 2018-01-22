package commandline;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Log {

	private final String logFileName = "toptrumps.log";
	private PrintWriter writeToLog;

	public Log() {

		try {
			writeToLog = new PrintWriter(logFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 1. Writes the contents of the complete deck once it has been read in and
	// constructed
	

	public void logDeck(Deck deck) {

		writeToLog.write("The contents of the deck is as follows:\n");
		ArrayList<Card> deckOfCards = deck.getDeck();

		for (int i = 0; i < deckOfCards.size(); i++) {
			String card = deckOfCards.get(i).cardToString();
			writeToLog.write(card);
		}
		dash();
	}
	
	// 2. The contents of the complete deck after it has been shuffled
	public void logShuffle(Deck deck) {
		writeToLog.write("The cards have been shuffled.\n");
		logDeck(deck);
	}
	

	// 3.The contents of the user’s deck and the computer’s deck(s) once they have
	// been allocated. Be sure to indicate which the user’s deck is and which the
	// computer’s deck(s) is.

	public void playerDecks(ArrayList<Player> players) {

		// Writes each players name and the cards they currently have to file
		
		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			writeToLog.write("User deck: " + name);
			ArrayList<Card> deck = players.get(i).playerDeck.getDeck();
					
			for (int j = 0; j < deck.size(); j++) {

				String userCard = deck.get(j).cardToString();
				writeToLog.write(userCard);

			}
			dash();
		}
	}

	// 4. The contents of the communal pile when cards are added or removed from it

	public void communalPile(ArrayList<Card> winnerPile) {

		writeToLog.write("The communal pile currently contains the following:\n");

		for (int i = 0; i < winnerPile.size(); i++) {

			String communalCard = winnerPile.get(i).cardToString();
			writeToLog.write(communalCard);

		}
		dash();
	}

	// 5. The contents of the current cards in play (the cards from the top of the
	// user’s deck and the computer’s deck(s))

	public void cardsInPlay(ArrayList<Card> winnerPile) {

		writeToLog.write("The current cards in play are:\n");

		for (int i = 0; i < winnerPile.size(); i++) {

			String communalCard = winnerPile.get(i).cardToString();
			writeToLog.write(communalCard);

		}
		dash();
	}

	// 6. The category selected and corresponding values when a user or computer
	// selects a category
	public void categoryChosen(ArrayList<Player> players) {

		int index = players.get(0).getChosenCat();
		String category = players.get(0).topCard.getSelectedCategory(index);
		writeToLog.write("The chosen category is: " + category + "\n");

		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			int value = players.get(i).topCard.getSelectedValue();
			writeToLog.write(name + "'s " + category + " =" + value + "\n");
		}
	}

	// 7. The contents of each deck after a round

	public void postRound(ArrayList <Player> players) {
		writeToLog.write("Post Round Decks:\n");
		playerDecks(players);
	}

	// 8. The winner of the game
	public void logWinner(Player winner) {
		// again I think we will need a new method in player to reveal who each player
		// is
		String win = winner.toString();
		writeToLog.write("The winner is: " + win + " !!!");
		dash();
	}

	public void close() {
		writeToLog.close();
	}

	public void dash() {
		StringBuilder dash = new StringBuilder();

		for (int i = 0; i < 30; i++) {
			dash.append("-");
		}
		String d = dash.toString();
		writeToLog.write(d);
	}

}
