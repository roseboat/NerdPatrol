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
	private Player p2;
	private Player winner;
	private int numPlayers;
	private Deck deck;
	private ArrayList<Card> winnerPile = new ArrayList<Card>();
	private static ArrayList<Player> players;
	private static Log myLog;

	// DANTE SORT THIS OUT
	public GameManager(String playerName, int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();

		
		 myLog = new Log();
		 myLog.logDeck(deck);
		
		// deck is shuffled
		Collections.shuffle(deck.getDeck());
		
		myLog.logShuffle(deck);
		


		// deck is shuffled
		Collections.shuffle(deck.getDeck());

		Deck[] cards = deck.advancedSplit(this.numPlayers);
		humanPlayer = new Human(playerName, cards[0]);
		players = new ArrayList<Player>();
		players.add(humanPlayer);
		for (int i = 1; i < cards.length; i++) {
			players.add(new Computer("Computer " + i, cards[i]));
		}

		 myLog.playerDecks(players);
		
	randomiseOrder();
	}

	public void checkDecks() {
		for (Player each : players)
			if (each.playerDeck.getDeckSize() < 1) {
				players.remove(each);
			}
	}

	public void randomiseOrder() {
		Collections.shuffle(players);
		p1 = players.get(0);
	}

	// removes a player from the list
	public void removeKebab(int i) {

		players.remove(i);
	}

	public void initiateRound() {
		roundStarter ();
		for (int i = 0; i < players.size(); i++) {
			// checks to see if any players have run out of cards
			if (players.get(i).playerDeck.getDeckSize() < 1) {
				removeKebab(i);
				i--;
			}
			// all players draw their first card
			players.get(i).drawCard(); // *****CHANGED this method so it doesnt remove the card
		}
		
		// how many cards each player remaining has
		for (int i = 0; i < players.size(); i++) {
			System.err.println(
					players.get(i).getName() + " has " + players.get(i).playerDeck.getDeckSize() + " cards left");
		}
		
		if (players.size() > 1) {

			// displays starting player's card
			if (p1 == humanPlayer)
				p1.promptUser();

			// first player selects the category for all players
			// humans type in input, NPC always selects highest figure
			// using index, it corresponds to the index of the value held in the cardValues
			// array in Card
			int index = p1.chooseCategory();

			for (int i = 0; i < players.size(); i++) {

				// sets the value of the chosen category to selectedValue
				// of every player
				players.get(i).topCard.setSelectedValue(index);
				players.get(i).setChosenCat(players.get(i).topCard.getSelectedValue());


				myLog.categoryChosen(players);

				// adds card to the winner's pile
				winnerPile.add(players.get(i).topCard);

				// remove top cards from player's decks
				players.get(i).playerDeck.getDeck().remove(0); // *****REMOVES TOP CARD HERE

			}

			 myLog.cardsInPlay(winnerPile);
	
			// check winnerPile size
			int cardsToWin = winnerPile.size()-1;
			System.err.println("There are " + cardsToWin + " cards to play for.");
			decideWinner(index);
		} else

			 myLog.close();
			endGame();
	}
	public void roundStarter () {
		for(;;) {
		System.out.println("type 'drawcard' to start round");
		Scanner sc = new Scanner(System.in);
		String startNextRound = sc.nextLine();
		
		if (startNextRound.matches("drawcard")) {
			System.out.println("*******************\r\n");
			break;
		} else 
			System.out.println("you didnt enter 'drawcard'");
		}
	}
	public void endGame() {
		System.out.println(players.get(0).getName() + " has won the game!");
		System.exit(1);
	}

	public void decideWinner(int index) {

		if (players.size() > 1) {

			for (int i = 0; i < players.size(); i++) {

				// displays the category and value of each player's card
				String category = players.get(i).topCard.getSelectedCategory(index);
				int value = players.get(i).topCard.getSelectedValue();

				System.out.println("Player: " + players.get(i).getName() + " " + category + ":" + value);

			}
			// cards in winner pile given to the winner of the round
			// winner pile resets
			Collections.sort(players, Collections.reverseOrder());
			winner = players.get(0);

			// test for instances where winners are tied
			// pass to drawHandler method
			// otherwise, add cards to the winner's deck
			// and display winner

			if (players.get(0).compareTo(players.get(1)) == 0)
				drawHandler();
			else {
				// starting player of next round is the winner

				myLog.postRound(players);

				p1 = winner;
				winner.addToDeck(winnerPile);
				winnerPile.clear();
				System.out.println("The winner of this round is Player: " + winner.getName());
			}

		} else

			myLog.logWinner(winner);

			endGame();
	}

	// method handles the situation when there is a draw
	public void drawHandler() {

		myLog.communalPile(winnerPile);

		System.out.println("Round ended in a draw. The next round will be started.");
		initiateRound();
	}

	// every time it's the human player who starts, the deck is shuffled and divided
	// in the exact same way

	public static void main(String args[]) {

		GameManager gm = new GameManager("Bob", 5);

		while (players.size() > 1)
			gm.initiateRound();
	}

}
