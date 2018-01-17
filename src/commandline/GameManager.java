package commandline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameManager {
    
    private Player p1;
    private Player p2;
    private int numPlayers;
    private Deck deck;
    private ArrayList<Player> players;
    
    public GameManager(int numberOfPlayers){
    	this.numPlayers = numberOfPlayers;
    	this.deck = new Deck();
    	Deck[] cards = deck.advancedSplit(this.numPlayers);
    	players.add(new Human("bob", cards[0]));
    	for (int i = 1; i < cards.length; i++) {
    		players.add(new Computer("Computer " + i, cards[i]));
    	}
    	Collections.shuffle(players);
    }
    
    public void checkDecks() {
    	for (Player each : players)
    		if (each.playerDeck.getDeckSize() < 1) {
    			players.remove(each);
    		}
    }
    
    public void decideWinner(int index) {
    	

		p1.topCard.getSelectedValue(index);
		p2.topCard.getSelectedValue(index);
		
		Deck winnerPile= new Deck();

		Card[] c= new Card[]{p1.topCard, p2.topCard};
		Arrays.sort(c);

   // public static void main(String[] args) {
	//Deck deck = new Deck();
	//deck.testPrint();
  //  }

}
