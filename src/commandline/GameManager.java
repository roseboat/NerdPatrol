package commandline;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameManager {

	private Human humanPlayer;
	private Player p1;
	// p1 begins each "round" of top trumps
	private Player p2;
	private Player winner;
	private int numPlayers;
	private Deck deck;
	private ArrayList<Card> winnerPile = new ArrayList<Card>();
	private static ArrayList<Player> players;

	
	// DANTE SORT THIS OUT
	public GameManager(String playerName, int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
		
		// deck is shuffled
		Collections.shuffle(deck.getDeck());
		
		Deck[] cards = deck.advancedSplit(this.numPlayers);
		humanPlayer= new Human(playerName, cards[0]);
		players = new ArrayList<Player>();
		players.add(humanPlayer);
		for (int i = 1; i < cards.length; i++) {
			players.add(new Computer("Computer " + i, cards[i]));
		}
		randomiseOrder();
		
		//prints out how many cards each person has, for debugging 
		for (int i = 0; i <players.size(); i++) {
			System.err.println(players.get(i).getName()+" has "+ players.get(i).playerDeck.getDeckSize()+" cards left");
		}
	}
	
	public void checkDecks() {
		for (Player each : players)
			if (each.playerDeck.getDeckSize() < 1) {
				players.remove(each);
			}
	}
	
	public void randomiseOrder()	{
		Collections.shuffle(players);
		p1=players.get(0);
	}
	
	//removes a player from the list
	public void removeKebab(int i){

		   players.remove(i);
	}

	public void initiateRound() {
				
		// all players draw their first card
		for (int i=0; i<players.size(); i++)	{
			// checks to see if any players have run out of cards
			if (players.get(i).playerDeck.getDeckSize() < 1) {
				removeKebab(i);
				i--;
			}	
			players.get(i).drawCard(); // *****CHANGED this method so it doesnt remove the card
		}
		
		// displays starting player's card
		p1.promptUser();
		
		// first player selects the category for all players	
		// humans type in input, NPC always selects highest figure
		// using index, it corresponds to the index of the value held in the cardValues array in Card
		int index=p1.chooseCategory();
		
		
		for (int i=0; i<players.size(); i++)	{
			
			// sets the value of the chosen category to selectedValue
			// of every player
			players.get(i).topCard.setSelectedValue(index);
			players.get(i).setChosenCat(players.get(i).topCard.getSelectedValue());
			
			// adds card to the winner's pile
			winnerPile.add(players.get(i).topCard);
			
			
			//remove top cards from player's decks
			players.get(i).playerDeck.getDeck().remove(0); //*****REMOVES TOP CARD HERE 
			
		}
		//check winnerPile size
		System.out.println("There are "+winnerPile.size()+" cards to play for.");
		
		decideWinner(index);
		//prints out how many cards each person has, for debugging 
		for (int i = 0; i <players.size(); i++) {
			System.err.println(players.get(i).getName()+" has "+ players.get(i).playerDeck.getDeckSize()+" cards left");
		}
	}
	
	public void decideWinner(int index) {
		
		for (int i= 0; i< players.size(); i++) {
				
			// displays the category and value of each player's card
			String category=players.get(i).topCard.getSelectedCategory(index);
			int value=players.get(i).topCard.getSelectedValue();
			
			System.out.println ("Player: "+players.get(i).getName()+" "+category+":"+value);
			
		}
		// cards in winner pile given to the winner of the round
		// winner pile resets
		Collections.sort(players, Collections.reverseOrder());
		winner=players.get(0);
		
		// test for instances where winners are tied
		// pass to drawHandler method
		// otherwise, add cards to the winner's deck
		// and display winner
		
		if (players.get(0).compareTo(players.get(1))==0)
			drawHandler();
		else {
		// starting player of next round is the winner
		p1=winner;
		winner.addToDeck(winnerPile);
		winnerPile.clear();
		System.out.println("The winner of this round is Player: "+winner.getName());
		System.out.println();
		}	
	}	
	
	// method handles the situation when there is a draw
	public void drawHandler () {
		
		System.out.println("Round ended in a draw. The next round will be started.");
		System.out.println();
		initiateRound();
	}
	
	
	// some considerations: 
	// if a player runs out of cards & is removed from array, will that mess up for loops?
	// every time it's the human player who starts, the deck is shuffled and divided in the exact same way
	
	public static void main(String args[]){
//	    Deck dk = new Deck();
//	    Player p1 = new Human("Kappa", dk);
//	    p1.promptUser();
	    GameManager gm= new GameManager("Bob",5);
	    for (int i=0; i<20; i++)	{
	    		gm.initiateRound();
	    }
//	    gm.initiateRound();
//	    gm.removeKebab(2);
//	    gm.initiateRound();
	    
	    	
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
