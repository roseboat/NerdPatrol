package commandline;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Log class that is instantiated with a '-t' flag on starting the program.
 * Writes an extensive log of the program's operation to a 'toptrumps.log' file,
 * allowing for debugging.
 */

public class Log {

	private final String logFileName = "toptrumps.log";
	private PrintWriter writeToLog;

	/**
	 * Constructor which creates a new PrintWriter object and passes it the final
	 * String logFileName. Catches any file not found exceptions
	 */
	public Log() {

		try {
			writeToLog = new PrintWriter(logFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to write the contents of the complete deck to the log file
	 * Loops through the entire deck.
	 * 
	 * @param deck,
	 *            the entire card deck
	 */

	public void logDeck(Deck deck) {

		writeToLog.write("The contents of the deck is as follows:\r\n\r\n");
		ArrayList<Card> deckOfCards = deck.getDeck();

		for (int i = 0; i < deckOfCards.size(); i++) {
			String card = deckOfCards.get(i).cardToString();
			writeToLog.write(card + "\r\n\r\n");
		}
		dash();
	}
	

	/**
	 * Method called once the deck has been shuffled. Writes the contents of the new
	 * shuffled deck to the log file.
	 * 
	 * @param deck,
	 *            the shuffled deck
	 */

	public void logShuffle(Deck deck) {
		writeToLog.write("The cards have been shuffled.\r\n");
		logDeck(deck);
	}

	/**
	 * Writes each player's name and the cards they currently have to the log file.
	 * 
	 * @param players,
	 *            an array list of the players in the game
	 */

	public void playerDecks(ArrayList<Player> players) {

		// Loops through each player and prints their name
		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			writeToLog.write("User deck: " + name + "\r\n\r\n");
			ArrayList<Card> deck = players.get(i).playerDeck.getDeck();

			// If the player has 0 cards, this is written to the log file also
			if (deck.size() == 0) {
				writeToLog.write(name + " has no more cards!");
			}

			// Loops through a player's deck of cards and prints each out to the log file
			for (int j = 0; j < deck.size(); j++) {

				String userCard = deck.get(j).cardToString();
				writeToLog.write(userCard + "\r\n\r\n");
			}
			dash();
		}
	}

	/**
	 * Prints the contents of the communal pile when cards are added or removed from
	 * it
	 * 
	 * @param winnerPile
	 */
	public void communalPile(ArrayList<Card> winnerPile) {

		writeToLog.write("The communal pile currently contains the following:\r\n");

		for (int i = 0; i < winnerPile.size(); i++) {

			String communalCard = winnerPile.get(i).cardToString();
			writeToLog.write(communalCard + "\r\n");

		}
		dash();
	}

	/**
	 * Prints the contents of the current cards in play (from the top of the
	 * player's deck and the computer's deck(s))
	 * 
	 * @param topCards,
	 *            the top cards of each player
	 */
	public void cardsInPlay(ArrayList<Card> topCards) {

		writeToLog.write("The current cards in play are:\r\n\r\n");

		for (int i = 0; i < topCards.size(); i++) {

			String communalCard = topCards.get(i).cardToString();
			writeToLog.write(communalCard + "\r\n\r\n");

		}
		dash();
	}

	/**
	 * Prints the category selected and the corresponding values of each player,
	 * human and computer that will be compared to decide the winner.
	 * 
	 * @param category,
	 *            the category that the active player of the round has chosen
	 * @param players,
	 *            an array of the players in the game
	 */
	public void categoryChosen(String category, ArrayList<Player> players) {

		String c = category;

		writeToLog.write("The chosen category is: " + category + "\r\n");

		for (int i = 0; i < players.size(); i++) {

			String name = players.get(i).getName();
			int value = players.get(i).heldCard.getSelectedValue();
			writeToLog.write(name + "'s " + c + " =" + value + "\r\n");
		}
	}

	/**
	 * Prints the contents of each user's deck after a round
	 * 
	 * @param players,
	 *            the players in the game
	 */
	public void postRound(ArrayList<Player> players) {
		writeToLog.write("Post Round Decks:\r\n\r\n");
		playerDecks(players); // calls the playerDecks method to print the player's decks
	}

	/**
	 * Prints the winner of a round to the log file
	 * 
	 * @param roundWinner,
	 *            the Player object who has won the round
	 */
	public void logRoundWinner(Player roundWinner) {

		String roundWin = roundWinner.getName();
		writeToLog.write("\r\nThe winner of the round is " + roundWin + " !\r\n");
		dash();
	}

	/**
	 * Prints the winner of the entire game to the log file
	 * 
	 * @param gameWinner,
	 *            the Player object who has won the game
	 */
	public void logGameWinner(Player gameWinner) {

		String gameWin = gameWinner.getName();
		writeToLog.write("\r\nThe winner of the game is " + gameWin + " !!!\r\n");
		dash();
	}

	/**
	 * Closes the Print Writer object when the game has finished
	 */
	public void close() {
		writeToLog.close();
	}

	/**
	 * Method to create a dash divider that separates the different pieces of
	 * information in the log file
	 */
	public void dash() {
		StringBuilder dash = new StringBuilder();

		for (int i = 0; i < 50; i++) {
			dash.append("-");
		}
		String d = dash.toString();
		writeToLog.write("\r\n" + d + "\r\n");
	}

}
