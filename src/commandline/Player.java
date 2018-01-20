package commandline;

import java.util.ArrayList;

public abstract class Player implements Comparable<Player> {
	
	
	// DANTE LOOK IN HERE MAKE SURE IT'S ALL GOOD
	protected Deck playerDeck;
	private String name;
	protected Card topCard;
	private int chosenCat;
	private int chosenCatIndex;

	public Player(String name, Deck playerDeck) {
		this.name = name;
		this.playerDeck = playerDeck;
		this.chosenCat = -1; //Just to give it an initial value 
	}

	public void drawCard() {

		if (this.playerDeck.getDeckSize() > 0) {
			// there should never be a situation where a player receives an empty deck
			this.topCard = this.playerDeck.drawCard();
			this.playerDeck.getDeck().remove(0);
			
		}
		// top card represented by the index 0 in Deck arraylist
		// top card is removed from Deck
	}

	public void addToDeck(Deck winnerPile) {
			ArrayList<Card> winnerP = winnerPile.getDeck();
			playerDeck.addCards(winnerP);

		}
	
	public int compareTo(Player other){
	    if (this.chosenCat > other.chosenCat)
		return 1;
	    else if (this.chosenCat < other.chosenCat)
		return -1;
	    else
		return 0;
	}
	
	public int getChosenCat() {
	    return chosenCat;
	}

	public void setChosenCat(int chosenCat) {
	    this.chosenCat = chosenCat;
	}
		
	public int getChosenCatIndex() {
	    return chosenCatIndex;
	}

	public void setChosenCatIndex(int chosenCatIndex) {
	    this.chosenCatIndex = chosenCatIndex;
	}

	public String getName(){
	    return this.name;
	}
	
	//The other players lock in their categories based on who chose theirs
	public void respondToCategory(int index) {
	    chosenCat = topCard.getAllValues()[index];
	    System.out.println(this.name + " has followed on the category " + topCard.getSelectedCategory(index) + " with a value of " + this.chosenCat);
	}
	
	public void altChooseCategory(){
	    System.out.println("I cant pick");
	}
	
	

	public Card getTopCard() {
	    return topCard;
	}

	public abstract int chooseCategory();
	public abstract void promptUser();
	
}
