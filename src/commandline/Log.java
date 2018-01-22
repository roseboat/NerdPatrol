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

		writeToLog.write("The contents of the deck is as follows:\r\n\r\n");
		ArrayList<Card> deckOfCards = deck.getDeck();

		for (int i = 0; i < deckOfCards.size(); i++) {
			String card = deckOfCards.get(i).cardToString();
			writeToLog.write(card + "\r\n\r\n");
		}
		dash();
	}

	// 2. The contents of the complete deck after it has been shuffled
	public void logShuffle(Deck deck) {
		writeToLog.write("The cards have been shuffled.\r\n");
		logDeck(deck);
	}

	// 3.The contents of the user's deck and the computer's deck(s) once they have
	// been allocated. Be sure to indicate which the user's deck is and which the
	// computer's deck(s) is.

	public void playerDecks(ArrayList<Player> players) {

		// Writes each players name and the cards they currently have to file

		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			writeToLog.write("User deck: " + name + "\r\n\r\n");
			ArrayList<Card> deck = players.get(i).playerDeck.getDeck();

			for (int j = 0; j < deck.size(); j++) {

				String userCard = deck.get(j).cardToString();
				writeToLog.write(userCard + "\r\n\r\n");

			}
			dash();
		}
	}

	// 4. The contents of the communal pile when cards are added or removed from it

	public void communalPile(ArrayList<Card> winnerPile) {

		writeToLog.write("The communal pile currently contains the following:\r\n");

		for (int i = 0; i < winnerPile.size(); i++) {

			String communalCard = winnerPile.get(i).cardToString();
			writeToLog.write(communalCard + "\r\n");

		}
		dash();
	}

	// 5. The contents of the current cards in play (the cards from the top of the
	// user's deck and the computer's deck(s))

	public void cardsInPlay(ArrayList<Card> winnerPile) {

		writeToLog.write("The current cards in play are:\r\n\r\n");

		for (int i = 0; i < winnerPile.size(); i++) {

			String communalCard = winnerPile.get(i).cardToString();
			writeToLog.write(communalCard + "\r\n\r\n");

		}
		dash();
	}

	// THIS ONE DOES NOT WORK

	// 6. The category selected and corresponding values when a user or computer
	// selects a category
	public void categoryChosen(ArrayList<Player> players) {

		int index = players.get(0).getChosenCat();
		String category = players.get(0).topCard.getSelectedCategory(index);
		writeToLog.write("The chosen category is: " + category + "\r\n");

		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			int value = players.get(i).topCard.getSelectedValue();
			writeToLog.write(name + "'s " + category + " =" + value + "\r\n");
		}
	}

	// 7. The contents of each deck after a round

	public void postRound(ArrayList<Player> players) {
		writeToLog.write("Post Round Decks:\r\n\r\n");
		playerDecks(players);
	}

	// 8. The winner of the game
	public void logRoundWinner(Player roundWinner) {

		String roundWin = roundWinner.getName();
		writeToLog.write("\r\nThe winner of the round is " + roundWin + " !\r\n");
		dash();
	}

	public void logGameWinner(Player gameWinner) {

		String gameWin = gameWinner.getName();
		writeToLog.write("\r\nThe winner of the game is " + gameWin + " !!!\r\n");
		dash();
	}

	public void close() {
		writeToLog.close();
	}

	public void dash() {
		StringBuilder dash = new StringBuilder();

		for (int i = 0; i < 50; i++) {
			dash.append("-");
		}
		String d = dash.toString();
		writeToLog.write("\r\n" + d + "\r\n");
	}

}
