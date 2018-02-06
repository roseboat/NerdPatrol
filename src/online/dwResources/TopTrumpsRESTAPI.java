package online.dwResources;

import commandline.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
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
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
		deckFile=conf.getDeckFile();
		numPlayers=conf.getNumAIPlayers()+1;	
	}
	
	@GET
	@Path("/setPlayers")
	@Consumes (MediaType.APPLICATION_JSON)
	public void setPlayers (@QueryParam("Number") int Number) throws IOException {
		numPlayers=Number+1;
		System.err.println("the number of players: "+numPlayers);
		startGame();
	}
	
	
	// returns an index
	// depending on the button pressed
	// index is used to set the chosen category
	// prints the chosen category & value on console (for testing)
	@GET
	@Path("/selectCategory")
	public String selectCategory(@QueryParam("Number") int Number) throws IOException {
		catIndex = Number - 1;

		for (Player p : players) {
			p.getHeldCard().setSelectedValue(catIndex);
			// assigns the above value to each player
			p.setChosenCat(p.getHeldCard().getSelectedValue());
			winnerPile.add(p.getHeldCard());
			p.getDeck().remove(0);
		}
		Collections.sort(players, Collections.reverseOrder());

		winner = players.get(0);
		Card activeCard = activePlayer.getHeldCard();
		activeCard.setSelectedValue(catIndex);
		System.err.println(players.toString());
		System.err.println(winner.getName());

		// System.err.println("The chosen category is: "+
		// activeCard.getSelectedCategory(catIndex)+" with a value of
		// "+activeCard.getSelectedValue());

		return winner.getName();

	}
	
	

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

	}
	
	public void randomiseOrder() {
		//Collections.shuffle(players);

		activePlayer = players.get(0);
		
	}
	
	public void removePlayer(int i) {
		players.remove(i);
	}
	
	
	public void initiateRound() throws JsonProcessingException {
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

				// displays starting player's card
				activePlayer.promptUser();

				// first player selects the category for all players
				// index corresponds to the index of the value held in the cardValues array in Card
				int index = activePlayer.chooseCategory();

				for (int i = 0; i < players.size(); i++) {

				
					//sets the value that each player has for the chosen category on their top card
					players.get(i).getHeldCard().setSelectedValue(index);
					//assigns the above value to each player 
					players.get(i).setChosenCat(players.get(i).getHeldCard().getSelectedValue());

					// adds card to the winner's pile
					winnerPile.add(players.get(i).getHeldCard());

					// remove top cards from player's decks
					players.get(i).getDeck().remove(0);
				}

				decideWinner(index);

			} else
				endGame();
		}
	}
	
	public void decideWinner(int index) throws JsonProcessingException {

		// log bit - could be put in log class
		String category = "";
		for (int i = 0; i < players.size(); i++) {
			// gets the category
			category = players.get(i).getHeldCard().getSelectedCategory(index);
		}

		// cards in winner pile given to the winner of the round
		// winner pile resets
		Collections.sort(players, Collections.reverseOrder());
		winner = players.get(0);

		if (winner.compareTo(players.get(1)) == 0)
			drawHandler();
		else {
			// starting player of next round is the winner

			activePlayer = winner;
			winner.addToDeck(winnerPile);
			winnerPile.clear();

			// increment player wins count
			incrementPlayerWins();
		}
	}

	public void drawHandler() throws JsonProcessingException {

		// increment drawCount
		numDraws++;

		// return to round loop
		initiateRound();
	}
	
	
	
	@GET
	@Path("/endGame")
	public String endGame() throws JsonProcessingException {
		gameWinner = players.get(0);
		numRounds = 0;
		numDraws = 0;
		for (int i = 0; i < playerWinCounts.length; i++) {
			playerWinCounts[i] = 0;
		}

		String gameWinnerString = oWriter.writeValueAsString(gameWinner.getName());
		return gameWinnerString;
	}

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
	
	
	
	
	@GET
	@Path("/activePlayer")
	public String activePlayer() throws IOException {
	    System.err.println("Active player is " + activePlayer.getName());
		return activePlayer.getName();
	}
	
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbitary Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		System.out.println(listAsJSONString);
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/printer")
	public void printer(@QueryParam("Word") String Word) throws IOException {
		System.out.println(Word);
	}
	
	@GET
	@Path("/cardTest")
	public String cardTest() throws IOException{
		
//		Card x = players.get(0).getTopCard();
		Card x = new Card("DantsBants", 6,2,8,1,7,"style", "manners", "bants", "hygene", "dabs");
		String s1 = oWriter.writeValueAsString(x);
		System.out.println(s1);
		return s1;
	}
	
	@GET
	@Path("/moreCardTest")
	public String moreCardTest() throws IOException{
		
//		Card x = players.get(0).getTopCard();
		Card x = new Card("DantsBants", 6,2,8,1,7,"style", "manners", "bants", "hygene", "dabs");
		Card y = new Card("Chaddy", 1,2,3,4,1,"style", "manners", "bants", "hygene", "dabs");
		Card z = new Card("Roseboat", 9,4,6,3,2,"style", "manners", "bants", "hygene", "dabs");
		Card a = new Card("KilliamWirrage", 5,2,6,8,8,"style", "manners", "bants", "hygene", "dabs");
		Card b = new Card("Cowlvin", 9,8,7,6,5,"style", "manners", "bants", "hygene", "dabs");
		Card[] array = {x,y,z,a,b};
		String s1 = oWriter.writeValueAsString(array);
		System.out.println(s1);
		return s1;
	}
	

	
	@GET
	@Path("/sendCardArray")
	public String sendCardArray() throws IOException {
		Card[] cards = new Card[numPlayers];

		for (int i = 0; i < numPlayers; i++) {
			players.get(i).drawCard();
			switch(players.get(i).getName()){
			case("Human Player"):
			    cards[0] = players.get(i).getHeldCard();
			    continue;
			case("Computer 1"):
			    cards[1] = players.get(i).getHeldCard();
			    continue;
			case("Computer 2"):
			    cards[2] = players.get(i).getHeldCard();
			    continue;
			case("Computer 3"):
			    cards[3] = players.get(i).getHeldCard();
			    continue;
			case("Computer 4"):
			    cards[4] = players.get(i).getHeldCard();
			    continue;
			default:
			    System.err.println("There is no player");			
			}
		}

		String cardArray = oWriter.writeValueAsString(cards);
		System.err.println(cardArray);
		return cardArray;

	}
	
	
	@GET
	@Path("/showStats")
	/**
	 * Method to display the game statistics on the webpage
	 */
	public String gameStats() throws IOException {
		Database db = new Database();
		String x = db.getGameStatistics();
		db.closeConnection();
		db = null;

		String xAsJsonString = oWriter.writeValueAsString(x);
		return xAsJsonString;

	}
	
	@GET
	@Path("/printWinner")
	/**
	 * Method to display the winner of the round
	 */
	public String printWinner() throws IOException {
		
		String x = winner.getName();
		String xAsJsonString = oWriter.writeValueAsString(x);
		return xAsJsonString;
	}
	
	
	@GET
	@Path("/cardPile")
	public String cardPile() throws IOException {
		int test = players.size();
		
//		for (int i = 0; i < players.size(); i++) {
//			winnerPile.add(players.get(i).getTopCard());
//			if (players.get(0).compareTo(players.get(1)) == 0)
//			test++;
//			else
//			test = players.size();
//		}
		
		String xAsJsonString = oWriter.writeValueAsString(test);
		return xAsJsonString;
	}

	
}
