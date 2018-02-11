package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Maintains an ArrayList of card objects. Acts as a collection of Card objects
 * and enables cards to be exchanged and tracked. At no point can there ever be
 * more cards in the game than in the deck.
 * 
 * Deck can take two forms. The main deck which contains all cards but also the
 * decks that are supplied to the players.
 */

public final class Deck {

	private ArrayList<Card> deck;
	private String[] categories;

	private final String DECK_NAME = "StarCitizenDeck.txt";

	/**
	 * Constructs deck from the supplied text file. Acts as a wrapper to an
	 * ArrayList.
	 * 
	 * @see loadDeck
	 */
	protected Deck() {
		deck = new ArrayList<Card>();
		loadDeck(DECK_NAME);
	}

	/**
	 * A constructor for the online version of Top Trumps
	 * 
	 * @param deckName
	 */
	public Deck(String deckName) {
		deck = new ArrayList<Card>();
		loadDeck(deckName);
	}

	/**
	 * Constructs a new Deck object based on a supplied array list of cards.
	 * 
	 * @param pileOfCards,
	 *            arraylist of Card to act as a Deck
	 */
	protected Deck(ArrayList<Card> pileOfCards) {
		deck = pileOfCards;
	}

	 /**
	  * Accessor method for the Deck class.
	  * @return an array list of cards, ie. a deck
	  */
	 public ArrayList<Card> getDeck() {
	 return deck;
	 }
	
	/**
	 * Adds a card to the end of the deck.
	 * 
	 * @param card,
	 *            Card object to be appended
	 */
	protected void addCard(Card card) {
		deck.add(card);
	}
	
	/**
	 * Appends an ArrayList of cards to the end of the deck.
	 * 
	 * @param newCards,
	 *            Arraylist to be added at the bottom of the deck
	 */
	protected void addCards(ArrayList<Card> newCards) {
		deck.addAll(newCards);
	}

	/**
	 * Loads information from the deck and adds it to the Deck object
	 */
	private void loadDeck(String deckName) {

		FileReader fr = null;
		Scanner in = null;
		try {
			try {
				// count to get the first line in text file (it is unique)
				int count = 0;

				// reads file, and puts scanner around the reader
				fr = new FileReader(DECK_NAME);
				in = new Scanner(fr);

				// gets data line by line
				String titleLine = "";
				String dataLine = "";
				while (in.hasNextLine()) {
					// if it's the first line
					if (count < 1) {
						titleLine = in.nextLine(); // gets category titles if first line

						storeCategories(titleLine);
						// System.err.println(titleLine);
					} else {
						dataLine = in.nextLine(); // gets data from other lines
						buildDeck(dataLine); // sends each line of info to deck building class
					}

					count++;
				}

			} finally {
				if (fr != null) {
					fr.close();
					in.close();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Add a new card to the end of the deck
	 * 
	 * @param cardInfo,
	 *            String representation of the category values associated with the
	 *            card
	 */
	private void buildDeck(String cardInfo) {
		Card card = new Card(cardInfo, categories);
		deck.add(card);
	}

	/**
	 * Stores the names of the categories associated with the deck of cards
	 * 
	 * @param titleLine,
	 *            String showing names of categories separated by whitespace
	 */
	private void storeCategories(String titleLine) {
		categories = new String[6];
		titleLine = titleLine.substring(12);
		categories = titleLine.split(" ");
	}

	/**
	 * Shuffles the card objects in the Deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * Draws the top card from a deck
	 * 
	 * @return the top card in a deck of cards (the first card in a deck array)
	 */
	public Card drawCard() {
		return deck.get(0);
	}

	/**
	 * Returns the size of the deck
	 * 
	 * @return int value representing the size of deck
	 */
	public int getDeckSize() {
		return deck.size();
	}

	/**
	 * Returns the array holding the names of the categories of the deck
	 * 
	 * @return categories, String array of the names of categories
	 */
	protected String[] getCategories() {
		return categories;
	}

	/**
	 * Split the current deck into two. The point of the split is determined by
	 * quotient of the size of the deck and divisor. The returned Deck object
	 * represents the latter part of the original deck result from the split
	 * 
	 * @param divisor,
	 *            how the deck will be split, eg if 2 it be split in half, if 3
	 *            original deck is a third and returned deck 2 thirds
	 * @return splitDeck, new Deck object resulting from the split of the original
	 *         deck
	 */
	private ArrayList<Card> split(int divisor) {
		ArrayList<Card> splitDeck = new ArrayList<Card>(deck.subList((deck.size() / divisor), deck.size()));
		deck.removeAll(splitDeck);
		return splitDeck;
	}

	/**
	 * Advanced split splits the decks corresponding to the number of players within
	 * the game. It returns an array of decks to be used to distribute the cards
	 * among the players.
	 * 
	 * @param numberOfPlayers,
	 *            number of players within the game includes computer opponents
	 * @return decks, an array of decks one for each player
	 */
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