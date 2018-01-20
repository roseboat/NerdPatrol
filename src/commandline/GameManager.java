package commandline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GameManager {

	private Human humanPlayer;
	private Player p1;
	// p1 begins each "round" of top trumps
	private Player p2;
	private Player winner;
	private int numPlayers;
	private boolean gameOver = false;
	private Deck deck;
	private Deck winnerPile= new Deck(new ArrayList<Card>());
	private ArrayList<Player> players;

	
	// DANTE SORT THIS OUT
	public GameManager(int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
		Deck[] cards = deck.advancedSplit(this.numPlayers);
		humanPlayer= new Human("Ron", cards[0]);
		players = new ArrayList<Player>();
		players.add(humanPlayer);
		for (int i = 1; i < cards.length; i++) {
			players.add(new Computer("Computer " + i, cards[i]));
			p1=players.get(0);
		}
		//Collections.shuffle(players);
		
	}
	
	public void checkDecks() {
		for (Player each : players)
			if (each.playerDeck.getDeckSize() < 1) {
				players.remove(each);
			}
	}
	
	public void initiateRound() {

		
		// first player selects the category for all players	
		// humans type in input, NPC always selects highest figure
		// using index
		int index=p1.chooseCategory();
		
		for (int i=0; i<players.size(); i++)	{
			
			// sets the value of the chosen category to selectedValue
			// of every player
			players.get(i).drawCard();
			players.get(i).topCard.setSelectedValue(index);
			
			// adds card to the winner's pile
			winnerPile.addCard(players.get(i).topCard);
		}
		
		decideWinner(index);
	}
	
	public void decideWinner(int index) {
		
		for (int i= 0; i< players.size(); i++) {
				
			// displays the category and value of each player's card
			String category=players.get(i).topCard.getSelectedCategory(index);
			int value=players.get(i).topCard.getSelectedValue();
			
			System.out.println ("Player: "+category+":"+value);
				
			// when the current player is not p1
			// compare stats of players
			// stores result as 0, 1 or -1
			if (players.get(i)!= p1)	{
				int result= players.get(i).topCard.compareTo(players.get(i+1).topCard);
					
				if (result== 1)	{
					winner=players.get(i);
				}	
				if (result==-1)	{
					winner=players.get(i+1);
				}
				if (result== 0 && i==players.size()) {
					// result in a draw
					// if two values are the same, returning 0
					// && if at the end of the loop
				drawHandler();	
				}
			}
			
			// cards in winner pile given to the winner of the round
			// winner pile resets
			winner.addToDeck(winnerPile);
			winnerPile.getDeck().clear();
			System.out.println("The winner of this round is Player: "+winner);
		}
			
	}	
	
	// method handles the situation when there is a draw
	public void drawHandler () {
		
		initiateRound();
	}
	
	public boolean getGameOver(){
	    return gameOver;
	}
	
	//Player selects a category, everyone loads up values corresponding to the chosen category
	//Player that selects is always the first one in the array
	public void categoryPhase() {
	    for (Player p : players){
		p.drawCard();
	    }
	    players.get(0).altChooseCategory();
	    int i = players.get(0).getChosenCatIndex();
	    winnerPile.addCard(players.get(0).getTopCard());
	    for (int j = 1; j < players.size(); j++) {
		players.get(j).respondToCategory(i);
		winnerPile.addCard(players.get(j).getTopCard());
		
	    }
	}
	
	//the first player is assumed to have the biggest card initially
	//then compared against all other players, if there is a bigger one they get assigned as the player with the best card
	//cannot work out draws
	public void declareRoundWinner() {
	    boolean draw = false;
	    this.p1 = players.get(0);
	    
	    for (int i = 1; i < players.size(); i++){
		if (p1.compareTo(players.get(i)) == -1) {
		    p1 = players.get(i);
		}
	    }
	    //move the winner to the front of the arraylist
	    int index = players.indexOf(p1);
	    players.remove(p1);
	    
	    
	    //check for draws by taking the strongest player and comparing him
	    for (int i = 0; i < players.size(); i++){
		if (p1.compareTo(players.get(i)) == 0) {
		    draw = true; //change draw tag if a player is equivalent
		}
	    }
	    
	    if (draw) {
		System.out.println("It's a draw! " + p1.getName());
		players.add(index, p1); //In the case of the draw the original players gets to choose
	    } else {
		System.out.println("The winner of this round is " + p1.getName());
		players.add(0, p1);//New player placed at the start of the player list
		p1.playerDeck.addCards(winnerPile.getDeck());
		winnerPile.getDeck().clear();
	    }
	}
	
	
	public void endPhase() {
	    System.out.println("---------------------------------------");
	    for (int i = 0; i < players.size(); i++){
		if (players.get(i).playerDeck.getDeckSize() == 0) {
		    System.err.println("Player " + players.get(i).getName() + " has been elimated!");
		    players.remove(players.get(i));
		    i--;
		    
		}
		System.out.println(players.get(i).getName() + " has " + players.get(i).playerDeck.getDeckSize() + " cards left.");
	    }
	    if (this.players.size() <= 1){
		gameOver = true;
	    }
	}
	
	public void takeTurn(){
	    for (Player p : players){
		p.drawCard();
		p.altChooseCategory();
	    }
	}
	
	public static void main(String args[]){
	    GameManager gm = new GameManager(3);
	    while (!gm.getGameOver()){
		gm.categoryPhase();
		gm.declareRoundWinner();
		gm.endPhase();
	    }
	}
	
}

//		Deck winnerPile = new Deck();
//
//		Card[] c = new Card[] { p1.topCard, p2.topCard };
//		int winIndex = -1;
//		Card bigCard;
//		for (int i = 1; i < c.length; i++) {
//			if (c[i] > c[i - 1]) {
//
//			}
//		}
	
	// CALVIN WINNERWINSCARDS / LOSERLOSESCARDS



	
