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

    public static void main(String[] args) {
	Deck deck = new Deck();
	deck.testPrint();
    }

}
