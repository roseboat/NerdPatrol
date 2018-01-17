package commandline;

import java.util.ArrayList;

public class GameManager {
    
    private Player p1;
    private Player p2;
    private int numPlayers;
    private Deck deck;
    
    public GameManager(){
	this.deck = new Deck();
	this.numPlayers = 2;
	//this.p1 = new Player("Hugh", new ArrayList<Card>(this.deck.getCards().subList(0, (this.deck.getDeckSize()/this.numPlayers)));
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
