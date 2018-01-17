package commandline;

import java.util.ArrayList;

public class GameManager {
    
    private Player p1;
    private Player p2;
    private int numPlayers;
    private Deck deck;
    private int [] topCardValues;
    
    public GameManager(){
	this.deck = new Deck();
	this.numPlayers = 2;
	//this.p1 = new Player("Hugh", new ArrayList<Card>(this.deck.getCards().subList(0, (this.deck.getDeckSize()/this.numPlayers)));
    }
    
    //Divide cards between players
    public void divideCards(int numPlayers){
	
    }
    
    public void decideWinner(int index) {
    	
//		int w = p1.topCard.getValue(index);
//		int x = p2.topCard.getValue(index);
		
		
		p1.topCard.setSelectedCategory(p1.topCard.getValue(index));
		p2.topCard.setSelectedCategory(p1.topCard.getValue(index));

    

   // public static void main(String[] args) {
	//Deck deck = new Deck();
	//deck.testPrint();
  //  }

}
