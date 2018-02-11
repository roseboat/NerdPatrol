package online.dwResources;

import commandline.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as
										// input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controlled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON
	 * strings easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private String deckFile;
	private int numPlayers;
	private Deck gameDeck;
	private Player activePlayer;
	private int catIndex;
	private int numRounds;
	private int numDraws;
	private Human humanPlayer;
	private Player winner;
	private Player gameWinner;
	private int[] playerWinCounts = new int[5];
	private static ArrayList<Player> players;
	private ArrayList<Card> winnerPile;

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the
	 * deck file and the number of AI players.
	 * 
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {

		deckFile = conf.getDeckFile();
		numPlayers = conf.getNumAIPlayers() + 1;
	}

	/**
	 * Sets the number of players to play the game. Acquires the input from the drop down menu in 
	 * the online guy and calls the startGame() method to initialise a game of toptrumps
	 * 
	 * @param Number, int to represent the number of players taking part in the game
	 * */
	@GET
	@Path("/setPlayers")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setPlayers(@QueryParam("Number") int Number) throws IOException {
		numPlayers = Number + 1;
		startGame();
	}

	/**
	 * Returns the name of the active player to update the GUI during the start of a round. The 
	 * active player is the player who gets to choose the category for that round of top trumps.
	 * 
	 * @return name, String name of the active player.
	 * */
	@GET
	@Path("/activePlayer")
	public String activePlayer() throws IOException {

		if (activePlayer != humanPlayer) {
			computerSelect();
		}

		return activePlayer.getName();
	}

	
	/**
	 * Method creates game based on number of players and declares a winnerPile (the storage
	 * are for cards played in a round as an Arraylist of card objects).
	 * It also loads the relevant information for the required deck and splits it amongst the players.
	 * This method also will randomise the order of players for
	 * who goes first. Round number is set to 1.
	 * */

	public void startGame() {
		gameDeck = new Deck(deckFile);
		Collections.shuffle(gameDeck.getDeck());

		Deck[] deck = gameDeck.advancedSplit(this.numPlayers);
		this.humanPlayer = new Human("Human Player", deck[0]);
		players = new ArrayList<Player>();
		players.add(humanPlayer);

		winnerPile = new ArrayList<Card>();
		for (int i = 1; i < deck.length; i++) {
			Player p = new Computer("Computer " + i, deck[i]);
			players.add(p);

		}
		randomiseOrder();
		numRounds = 1;
	}

	/**
	 * Randomises the order of the players. The active player is the one at the start of the array.
	 * */
	public void randomiseOrder() {
		Collections.shuffle(players);
		activePlayer = players.get(0);
	}


	/**
	 * This method is executed if it is the computer's turn and the computer selects the
	 * category to play for that round.
	 * 
	 * @return catString - String representation of the name of the category selected
	 * */
	@GET
	@Path("/computerSelect")
	public String computerSelect() {

		catIndex = activePlayer.chooseCategory();
		String catString = activePlayer.getHeldCard().getSelectedCategory(catIndex);
		return catString;
	}

	/**
	 * This method is executed if it is the human's turn and the human selects the
	 * category to play for that round using the appropriate button in the GUI
	 * 
	 * @param Number - int representation of index in cardArray for card selected
	 * @return catString - String representation of the name of the category selected
	 * */
	@GET
	@Path("/selectCategory")
	public String selectCategory(@QueryParam("Number") int Number) throws IOException {
		catIndex = Number - 1;
		String catString = activePlayer.getHeldCard().getSelectedCategory(catIndex);
		return catString;

	}

	/**
	 * Method checks if players still have cards in their decks
	 * 
	 * @return count - the number of players with cards left
	 * */
	public int checkDecks() {
		int count = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null && players.get(i).getDeckSize() > 0)
				count++;
		}
		return count;
	}

	
	/** Round logic is based here. First, it loops through players to check
	 * if they have run out of cards via the checkDecks() method
	 * if more than one player decks remain then increase round number
	 * and loop through players that are not null. Retrieves there held card
	 * values then remove that card from top of deck
	 * compares this card with every other remaining players held card 
	*/

	@GET
	@Path("/processRound")
	public String processRound() throws JsonProcessingException {

		for (int i = 0; i < players.size(); i++) {
			// checks to see if any players have run out of cards
			if (players.get(i) != null && players.get(i).getDeckSize() < 1) {
				players.set(i, null);
			}
		}

		if (checkDecks() > 1) {

			numRounds++;

			for (Player p : players) {
				if (p != null) {
					p.getHeldCard().setSelectedValue(catIndex);
					// assigns the above value to each player
					p.setChosenCat(p.getHeldCard().getSelectedValue());

					p.getDeck().remove(0);
				}
			}
			compareCards();
			
			//if draw return top two players, else give top player winnerPile and clear it
			if (players.get(0).compareTo(players.get(1)) == 0) {
				String draw = "Draw between " + players.get(0).getName() + " & " + players.get(1).getName();
				System.err.println("DRAW~~~~~~~~~~~~~");
				return draw;
			} else {

				winner = players.get(0);

				// winner gets winner pile - cards are added to pile in
				activePlayer = winner;
				winner.addToDeck(winnerPile);

				incrementPlayerWins();
				winnerPile.clear();

				return winner.getName();
			}
		} else {
			System.err.println(winner.getName() + " HAS WON THE GAME#################################");
			return "EndGame";
		}
	}
	
	/**
	 * Method is used to sort the player array. The winning player is sorted towards the
	 * start of the array. It accounts for null values in players and moves them towards
	 * the back of the array.
	 * It is used to determine the winner or if a draw has occurred
	 * */
	public void compareCards() {
		Collections.sort(players, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				if (p1 == null) {
					return 1;
				} else if (p2 == null) {
					return -1;
				} else {
					return (p1.compareTo(p2) * -1);
				}
			}
		});	
	}

	/**
	 * Method to end game when only one deck is left.
	 * Game Stats are saved and resets records
	 * 
	 * @return gameWinnerString - String name of the winner of the game
	 * */
	@GET
	@Path("/endGame")
	public String endGame() throws JsonProcessingException {
		String gameWinnerName = "";
		if (checkDecks() == 1) {
			gameWinner = players.get(0);
			gameWinnerName = gameWinner.getName();
			saveGameStats();
			numRounds = 0;
			numDraws = 0;

			for (int i = 0; i < playerWinCounts.length; i++) {
				playerWinCounts[i] = 0;
			}
		}
		String gameWinnerString = oWriter.writeValueAsString(gameWinnerName);
		return gameWinnerString;
	}

	/**
	 * Method is called at the end of the round. It increments the number of
	 * round wins for the winner of the round.
	 * */
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

	/**
	 * Constructs an array of card objects to be displayed in the GUI. The cards
	 * are sorted in accordance with the order of players. Human first then the computer players.
	 * This is done to ensure cards are being loaded in the correct slots within the GUI.
	 * 
	 * @return cardArray - JSON String list of JSON objects used to represent cards
	 * */
	@GET
	@Path("/sendCardArray")
	public String sendCardArray() throws IOException {
		Card[] cards = new Card[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i) != null && players.get(i).getDeckSize() > 0) {
				players.get(i).drawCard();
				switch (players.get(i).getName()) {
				case ("Human Player"):
					cards[0] = players.get(i).getHeldCard();
					continue;
				case ("Computer 1"):
					cards[1] = players.get(i).getHeldCard();
					continue;
				case ("Computer 2"):
					cards[2] = players.get(i).getHeldCard();
					continue;
				case ("Computer 3"):
					cards[3] = players.get(i).getHeldCard();
					continue;
				case ("Computer 4"):
					cards[4] = players.get(i).getHeldCard();
					continue;
				default:
					System.err.println("There is no player");
				}
			}
		}

		// add cards to winner pile here
		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i) != null && players.get(i).getDeckSize() > 0) {
				winnerPile.add(players.get(i).getHeldCard());
			}
		}

		String cardArray = oWriter.writeValueAsString(cards);
		return cardArray;

	}
	
	/**
	 * Returns the number of the active round
	 * 
	 * @return numRoundsString - String representation of round number
	 * */
	@GET
	@Path("/roundNumber")
	public String roundNumber() throws JsonProcessingException {

		String numRoundsString = oWriter.writeValueAsString(numRounds);
		return numRoundsString;

	}

	/**
	 * Method to display the winner of the round
	 * 
	 * @return xAsJsonString - String name of the winner of the game
	 */
	@GET
	@Path("/printWinner")
	public String printWinner() throws IOException {

		String x = winner.getName();
		String xAsJsonString = oWriter.writeValueAsString(x);
		return xAsJsonString;
	}
	
	/**
	 * Returns the number of cards within the communal pile
	 * 
	 * @return xAsJsonString - String number of cards in communal pile
	 * */
	@GET
	@Path("/cardPile")
	public String cardPile() throws IOException {
		int size = winnerPile.size();

		String xAsJsonString = oWriter.writeValueAsString(size);
		return xAsJsonString;
	}

	/**
	 * Returns the size of the deck of each player as an array. The array is in the same
	 * order as the card containers are loaded in the GUI.
	 * 
	 * @return handArray - String array of numbers to represent the size of each players deck.
	 * */
	@GET
	@Path("/cardsLeft")
	public String cardsLeft() throws IOException {
		int[] hands = new int[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i) != null) {

				switch (players.get(i).getName()) {
				case ("Human Player"):
					hands[0] = players.get(i).getDeckSize();
					continue;
				case ("Computer 1"):
					hands[1] = players.get(i).getDeckSize();
					continue;
				case ("Computer 2"):
					hands[2] = players.get(i).getDeckSize();
					continue;
				case ("Computer 3"):
					hands[3] = players.get(i).getDeckSize();
					continue;
				case ("Computer 4"):
					hands[4] = players.get(i).getDeckSize();
					continue;
				default:
					System.err.println("One of the player names is incorrect");
				}
			}
		}

		String handArray = oWriter.writeValueAsString(hands);
		return handArray;
	}

	/**
	 * Method to populate the saved statistics of the game
	 * 
	 * @return xAsJsonString - String representation of array containing pertinent information
	 * game statistics
	 * */
	@GET
	@Path("/statsTable")
	public String statsTable() throws IOException {
		Database db = new Database();
		int[] x = db.getGameStatisticsOnline();
		db.closeConnection();
		db = null;

		String xAsJsonString = oWriter.writeValueAsString(x);
		return xAsJsonString;
	}

	/**
	 * Saves game statistics to database at the end of the game.
	 */
	public void saveGameStats() {
		Database db = new Database();
		if (numPlayers == 2)
			db.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1]);
		else if (numPlayers == 3)
			db.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2]);
		else if (numPlayers == 4)
			db.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3]);
		else if (numPlayers == 4)
			db.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3]);
		else if (numPlayers == 5)
			db.gameStats(gameWinner.getName(), numRounds, numDraws, playerWinCounts[0], playerWinCounts[1],
					playerWinCounts[2], playerWinCounts[3], playerWinCounts[4]);
		db.closeConnection();
	}
}
