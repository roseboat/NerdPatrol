package commandline;
import java.util.ArrayList;
import java.util.Collections;

// just getting something there for you guys to see 

public class Deck {
	//ArrayList for Cards
	private ArrayList<Card> deck;
	
	//constructor
	public Deck() {
		deck = new ArrayList<Card>();
	}
	
	//method that can add to ArrayList - parameter could be string from textFile
	// think the game manager will use this method right? Or this class could read from file too
	public void buildDeck(String cardInfo) {
		deck.add(cardInfo);
	}
	
	//shuffles deck - got that method when from stack overflow - basically does it all
	private void shuffle() {
		Collections.shuffle(deck);
	}
	
	// getter for deckSize
	public int getDeckSize() {
		return deck.size();
	}
	
	
}