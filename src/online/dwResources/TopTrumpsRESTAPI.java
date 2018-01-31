package online.dwResources;

import commandline.*;

import java.io.IOException;
import java.util.ArrayList;
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
	private static ArrayList<Player> players;
	
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
		numPlayers=Number;
		System.out.println(numPlayers);
	}

//	// should we emulate game manager's functions here? or make a separate game manager class for online ver?
//	// cmd line ver game manager does not really work for the online ver
//	public void startGame() {
//		gameDeck= new Deck(deckFile);
//		Collections.shuffle(gameDeck.getDeck());
//		
//		Deck[] cards = gameDeck.advancedSplit(this.numPlayers);
//		Human humanPlayer = new Human("Bob", cards[0]);
//		players = new ArrayList<Player>();
//		players.add(humanPlayer);
//		for (int i = 1; i < cards.length; i++) {
//			players.add(new Computer("Computer " + i, cards[i]));
//		}
//	}
//	
//	@GET //or post?
//	@Consumes (MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String setCategoryChosen (int index)	{
//		Card c=activePlayer.getTopCard();
//		String category=c.getSelectedCategory(index);
//		return category;
//	}
//	
//	// API method to display the category names of each card
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public String displayCardCategories () throws JsonProcessingException	{
//		
//		Card c=activePlayer.getTopCard();
//		String [] categories=c.getCategories();
//		
//		String listAsJSONString = oWriter.writeValueAsString(categories);
//		return listAsJSONString;
//	}
//	
//	// API method to display the individual values of each player's active card
//	@POST
//	public String displayCategoryValues () throws JsonProcessingException {
//		
//		Card[] topCard= new Card[players.size()]; 
//		int[] values= new int[players.size()];
//		
//		for (int i=0; i<players.size(); i++)	{
//			topCard[i]=players.get(i).getTopCard();
//			values=topCard[i].getAllValues();
//		}
//		
//		String listAsJSONString = oWriter.writeValueAsString(values);
//		return listAsJSONString;
//			
//	}
//	
	
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
		
		// We can turn arbatory Java objects directly into JSON strings using
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
		Card x = new Card("DantsBants", 6,2,8,1,7);
		String s1 = oWriter.writeValueAsString(x);
		return s1;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
