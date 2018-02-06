package commandline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GameManager {

	private Human humanPlayer;
	private Player p1;
	// p1 begins each "round" of top trumps
	private Player winner;
	private Player gameWinner;
	private int numPlayers;
	private Deck deck;
	private ArrayList<Card> winnerPile = new ArrayList<Card>();
	private static ArrayList<Player> players;
	private static Log myLog;
	private int numRounds;
	private int numDraws;
	private Database database;
	private int[] playerWinCounts = new int[5];
	// for system.out formatting clarity
	private final String divider = "\r\n******************************************\r\n";

	public GameManager(String playerName, int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();

		myLog = new Log();
		myLog.logDeck(deck);

		// deck is shuffled
		Collections.shuffle(deck.getDeck());
		myLog.logShuffle(deck);

		// cards are dealt
		Deck[] cards = deck.advancedSplit(this.numPlayers);
		humanPlayer = new Human(playerName, cards[0]);
		players = new ArrayList<Player>();
		players.add(humanPlayer);
		for (int i = 1; i < cards.length; i++) {
			players.add(new Computer("Computer " + i, cards[i]));
		}
		myLog.playerDecks(players);

		// player to start game randomised
		randomiseOrder();
		// round initiated
		initiateRound();
	}

	// method to shuffle player order, to be used for who goes first in
	// beginning
	public void randomiseOrder() {
		Collections.shuffle(players);
		p1 = players.get(0);
	}

	// removes a player from the list
	public void removePlayer(int i) {
		players.remove(i);
	}

	public void initiateRound() {
		// check that two players are still in the game
		while (players.size() > 1) {

			for (int i = 0; i < players.size(); i++) {
				// checks to see if any players have run out of cards
				if (players.get(i).getDeckSize() < 1) {
					removePlayer(i);
					i--;
				}
				// all players draw their first card
				players.get(i).drawCard();
			}

			if (players.size() > 1) {

				// increment numRounds here
				numRounds++;
				// Displays round number
				System.out.println("Round number " + numRounds);

				// displays starting player's card
				p1.promptUser();

				// first player selects the category for all players
				// index corresponds to the index of the value held in the cardValues array in Card
				int index = p1.chooseCategory();

				for (int i = 0; i < players.size(); i++) {

				
					//sets the value that each player has for the chosen category on their top card
					players.get(i).getHeldCard().setSelectedValue(index);
					//assigns the above value to each player 
					players.get(i).setChosenCat(players.get(i).getHeldCard().getSelectedValue());

					// adds card to the winner's pile
					winnerPile.add(players.get(i).getHeldCard());

					// remove top cards from player's decks
					players.get(i).playerDeck.getDeck().remove(0);
				}

				myLog.cardsInPlay(winnerPile);

				decideWinner(index);

			} else
				endGame();
		}
	}

	public void endGame() {
		gameWinner = players.get(0);
		myLog.logGameWinner(gameWinner);
		myLog.close();
		System.out.println(gameWinner.getName() + " has won the game!");

		// save game stats here
		saveGameStats();

		// reset database statistics
		numRounds = 0;
		numDraws = 0;
		for (int i = 0; i < playerWinCounts.length; i++)
			playerWinCounts[i] = 0;
	}

	public void decideWinner(int index) {
		
			// log bit - could be put in log class
			String category = "";
			for (int i = 0; i < players.size(); i++) {
				// gets the category
				category = players.get(i).heldCard.getSelectedCategory(index);
			}
			myLog.categoryChosen(category, players);

			// cards in winner pile given to the winner of the round
			// winner pile resets
			Collections.sort(players, Collections.reverseOrder());
			winner = players.get(0);
			myLog.logRoundWinner(winner);

			if (players.get(0).compareTo(players.get(1)) == 0)
				drawHandler();
			else {
				// starting player of next round is the winner
				myLog.postRound(players);
				p1 = winner;
				winner.addToDeck(winnerPile);
				winnerPile.clear();
				System.out.println("The winner of this round is Player: " + winner.getName() + " who won with the "
						+ winner.heldCard.getName() + divider);

				// increment player wins count
				incrementPlayerWins();
			}			
	}

	// method handles the situation when there is a draw
	public void drawHandler() {

		myLog.communalPile(winnerPile);

		System.out.println("Round ended in a draw. The next round will be started." + divider);

		// increment drawCount
		numDraws++;
		
		//return to round loop 
		initiateRound();
	}

	// method to increment the number of wins a player has
	public void incrementPlayerWins() {
		if (winner.getName().equals(humanPlayer.getName()))
			playerWinCounts[0]++;
		else if (winner.getName().equals("Computer 1"))
			playerWinCounts[1]++;
		else if (winner.getName().equals("Computer 2"))
			playerWinCounts[2]++;
		else if (winner.getName().equals("Computer 3"))
			playerWinCounts[3]++;
		else if (winner.getName().equals("Computer 4"))
			playerWinCounts[4]++;
	}

	// method to save game statistics
	public void saveGameStats() {
		database = new Database();
		if (numPlayers == 2)
			database.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1]);
		else if (numPlayers == 3)
			database.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2]);
		else if (numPlayers == 4)
			database.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3]);
		else if (numPlayers == 4)
			database.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3]);
		else if (numPlayers == 5)
			database.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3], playerWinCounts[4]);
		database.closeConnection();
	}
}

// FOR TESTS
// how many cards each player remaining has FOR TESTING
/*
 * for (int i = 0; i < players.size(); i++) { System.err.println(
 * players.get(i).getName() + " has " + players.get(i).playerDeck.getDeckSize()
 * + " cards left"); }
 */
