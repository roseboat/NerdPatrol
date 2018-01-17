package commandline;

import java.util.ArrayList;
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
    
    //Divide cards between players
    public void divideCards(int numPlayers){
	
    }
    
    public void decideWinner(int index) {
    	
		switch (index) {

		case 1:
			choiceInt = topCard.getCargo();
			break;
		case 2:
			choiceInt = topCard.getfirePower();
			break;
		case 3:
			choiceInt = topCard.getRange();
			break;
		case 4:
			choiceInt = topCard.getSize();
			break;
		case 5:
			choiceInt = topCard.getSpeed();
			break;
		}

		topCard.setSelectedCategory(choiceInt);
    }
    
    
    

   // public static void main(String[] args) {
	//Deck deck = new Deck();
	//deck.testPrint();
  //  }

}
