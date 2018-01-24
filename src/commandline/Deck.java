package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * Maintains an ArrayList of card. Acts as a collection of Card objects and enables cards
 * to be exchanged and tracked. At no point can there ever be more cards in the game than
 * in the deck.
 * 
 * Deck can take two forms. The main deck which contains all cards but also the decks that
 * are supplied to the players.
 * */

public class Deck {
	
	private ArrayList<Card> deck;
	private String[] categories;
	
	private final String DECK_NAME = "StarCitizenDeck.txt";

	/**
	 * Constructs deck from the supplied text file. Acts as a wrapper to 
	 * an ArrayList.
	 * 
	 * @see loadDeck
	 * */
	public Deck() {
		deck = new ArrayList<Card>();
		loadDeck();
	}

	/**
	 * Constructs a new Deck object based on a supplied array list of cards.
	 * 
	 * @param pileOfCards, arraylist of Card to act as a Deck
	 * */
	public Deck(ArrayList<Card> pileOfCards) {
		deck = pileOfCards;
	}

	/**
	 * Appends an ArrayList of cards to the end of the deck.
	 * 
	 * @param newCards, Arraylist to be added at the bottom of the deck
	 * */
	public void addCards(ArrayList<Card> newCards) {
		deck.addAll(newCards);
	}

	
	// Calvin added this class
	public void addCard (Card card)	{
		deck.add(card);
	}

	// reads data in from text file, sends each line from text file into
	private void loadDeck() {

		FileReader fr = null;
		Scanner in = null;
		try {
			try {

				// count to get the first line in text file (it is unique)
				int count = 0;

				// reads file, and puts scanner around the reader
				fr = new FileReader("StarCitizenDeck.txt");
				in = new Scanner(fr);

				// gets data line by line
				String titleLine = "";
				String dataLine = "";
				while (in.hasNextLine()) {
					// if it's the first line
					if (count < 1) {
						titleLine = in.nextLine(); // gets category titles if first line - dunno what to do with this
													// yet
						storeCategories(titleLine);
						// System.err.println(titleLine);
					} else {
						dataLine = in.nextLine(); // gets data from other lines
						buildDeck(dataLine); // sends each line of info to deck building class
						// System.err.println(dataLine);
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

	// method that can add to ArrayList - parameter could be string from textFile
	// think the game manager will use this method right? Or this class could read
	// from file too
	public void buildDeck(String cardInfo) {
		Card card = new Card(cardInfo, categories);
		deck.add(card);
	}

	// method to parse and store the 5 category titles and "description"
	private void storeCategories(String titleLine) {
		categories = new String[6];
		titleLine = titleLine.substring(12);
		Scanner in = new Scanner(titleLine);
		// split deckInfo into the 6 separate words
		categories = titleLine.split(" ");
	}

	// shuffles deck - got that method when from stack overflow - basically does it
	// all
	private void shuffle() {
		Collections.shuffle(deck);
	}

	// gets top card - DO WE NEED THIS
	public Card drawCard() {
		return deck.get(0);
	}

	// getter for deckSize
	public int getDeckSize() {
		return deck.size();
	}

	// getter for array of categories
	public String[] getCategories() {
		return categories;
	}
	//method to print out individual cards based on the decks they are in and card counter
	public void testPrint(Deck x) {
		int i = 0;
		for (Card each : x.getDeck()) {
			System.out.println(i +" "+ each.cardToString());
			System.out.print("-----------------------\n");
			i++;
		}

	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> split(int numberOfPlayers) {

		ArrayList<Card> spdeck = new ArrayList<Card>(deck.subList((deck.size() / numberOfPlayers), deck.size())); // makes
																													// new
																													// list
		deck.removeAll(spdeck);
		return spdeck;

	}
	//this method allows the deck to be split based on the number of players in a game
	public Deck[] advancedSplit(int numberOfPlayers) {
		Deck[] decks = new Deck[numberOfPlayers];
		switch (numberOfPlayers) {
		case (2):
			Deck d1 = new Deck(this.split(2));
			decks[0] = this;
			decks[1] = d1;
			break;
		case (3):
			Deck d2 = new Deck(this.split(3));
			Deck d3 = new Deck(d2.split(2));
			decks[0] = this;
			decks[1] = d2;
			decks[2] = d3;
			break;
		case (4):
			Deck d4 = new Deck(this.split(2));
			Deck d5 = new Deck(this.split(2));
			Deck d6 = new Deck(d4.split(2));
			decks[0] = this;
			decks[1] = d5;
			decks[2] = d4;
			decks[3] = d6;
			break;
		case (5):
			Deck d7 = new Deck(this.split(5));
			Deck d8 = new Deck(d7.split(2));
			Deck d9 = new Deck(d7.split(2));
			Deck d10 = new Deck(d8.split(2));
			decks[0] = this;
			decks[1] = d7;
			decks[2] = d9;
			decks[3] = d8;
			decks[4] = d10;
		}

		return decks;

	}
}