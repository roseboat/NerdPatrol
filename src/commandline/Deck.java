package commandline;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// so far, 	Deck reads in data from text file
//			calls a Card constructor giving a line of data from the file eg 350r 1 9 2 3 0
//			Instantiates an ArrayList of these Cards
//			Also holds the category titles in a normal array

public class Deck {
	//ArrayList for Cards
	private ArrayList<Card> deck;
	
	//category titles
	private String[] categories;
	
	//constructor
	public Deck() {
		deck = new ArrayList<Card>();
		loadDeck();
	}
	
	//second constructor
	public Deck(ArrayList<Card> x) {
		deck = x;
	}
	
	// Calvin added this class
	// required to access the arraylist in Player class
	public ArrayList<Card> getCards ()	{
		return deck;
	}
	
	//reads data in from text file, sends each line from text file into 
	public void loadDeck() {

		FileReader fr = null;
		Scanner in = null;
		try {
			try {
				
				//count to get the first line in text file (it is unique)
				int count = 0;
				
				// reads  file, and puts scanner around the reader
				fr = new FileReader("StarCitizenDeck.txt");
				in = new Scanner(fr); 
				
				//gets data line by line
				String titleLine = "";
				String dataLine = "";
				while (in.hasNextLine()) {
					// if it's the first line
					if (count < 1) {
						titleLine = in.nextLine(); //gets category titles if first line - dunno what to do with this yet
						storeCategories(titleLine);
						//System.err.println(titleLine);
					}
					else {
						dataLine = in.nextLine(); //gets data from other lines
						buildDeck(dataLine); // sends each line of info to deck building class
						//System.err.println(dataLine);
					}
					
					
					count++;
				}
				
			} finally {
				// close if necessary
				if (fr != null) {
					fr.close();
					in.close();
				}
			}
		} catch (IOException ioe) {
			System.out.println("File i/o error");
			System.exit(1);
		}
	}
		
	//method that can add to ArrayList - parameter could be string from textFile
	// think the game manager will use this method right? Or this class could read from file too
	public void buildDeck(String cardInfo) {
		Card card = new Card(cardInfo);
		deck.add(card);
	}
	
	//method to parse and store the 5 category titles and "description"
	private void storeCategories(String deckInfo) {
		categories = new String[6];
		Scanner in = new Scanner(deckInfo);
		//split deckInfo into the 6 separate words
		categories = deckInfo.split(" ");
	}
	
	//shuffles deck - got that method when from stack overflow - basically does it all
	private void shuffle() {
		Collections.shuffle(deck);
	}
	
	//deal out cards - parameter for number of players
	public void deal(int x) {
		//so what happens with this - how do players get hands. new arrayLists? 
	}
	
	// getter for deckSize
	public int getDeckSize() {
		return deck.size();
	}
	
	//getter for array of categories
		public String[] getCategories() {
			return categories;
		}
	
	public void testPrint(Deck x) {
		int i =0;
		for (Card each : x.getDeck()) {
			System.out.println(each.cardToString());
			System.out.print("-----------------------  " + i);
			i++;
			}
		
			
	}

	
	
	
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> split(int numberOfPlayers){
		
		ArrayList<Card> spdeck = new ArrayList<Card>(deck.subList((deck.size()/numberOfPlayers), deck.size()));
		deck.removeAll(spdeck);
		return spdeck;
		
	}
	
	public static void main(String args[]) {
		Deck test = new Deck();
		
		System.out.println("-------------------------------------------------");
		test.testPrint(test);
		//test.shuffle();
		Deck splitDeck = new Deck(test.split(2));
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
		test.testPrint(test);
		System.out.println("-------------------------------------------------");
		test.testPrint(splitDeck);
		
}
	
}