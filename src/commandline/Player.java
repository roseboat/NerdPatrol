package commandline;
import java.util.ArrayList;

/**
 * Player Class models a Player within the game either human or a computer. In both cases similar attributes will be associated with each
 * but differ in the way they choose categories to play within a round. Humans will require input from the user while the computer
 * will always choose the category with the highest value.
 * */
public abstract class Player implements Comparable<Player>{
	
	protected Deck playerDeck;
	protected String name;
	protected Card heldCard;
	private int chosenCat;
	private int playerWins; 

	/**
	 * Constructs the player object with the name of the player and the deck that they will be using. ChosenCat corresponds to the category
	 * the player has chosen, it is initialised to -1 to act as a form of null value.
	 * */
	public Player(String name, Deck playerDeck) {
		this.name = name;
		this.playerDeck = playerDeck;
		this.chosenCat = -1; //Just to give it an initial value 
		playerWins = 0;
	}

	public void drawCard() {

		if (this.playerDeck.getDeckSize() > 0) {
			this.heldCard = this.playerDeck.drawCard();
			//this.playerDeck.getDeck().remove(0);
			
		}
	}
	
	/**
	 * CompareTo method implemented by comparable. Compares to player objects based on the value of the category they have chosen.
	 * 
	 * @param other, Player object to be compared against
	 * @return 1, chosen category of this object is larger than the other
	 * @return -1, chosen category of this object is smaller than the other
	 * @return 0, the chosen category of both players are equal
	 * */
	public int compareTo(Player other){
	    if (this.chosenCat > other.chosenCat)
	    	return 1;
	    else if (this.chosenCat < other.chosenCat)
	    	return -1;
	    else
	    	return 0;
	}
	
	/**
	 * Returns the card held by the player
	 * 
	 * @return heldCard, Card object held by the player
	 * */
	public Card getTopCard() 	{
		return heldCard;
	}
	
	/**
	 * Returns the size of the deck
	 * 
	 * @return size of the deck held by the player
	 * */
	public int getDeckSize()	{
		return playerDeck.getDeckSize();
	}

	/**
	 * Adds cards won by a player to their deck
	 * 
	 * @param winnerPile, deck of cards that all players have submitted during a round
	 * */
	public void addToDeck(Deck winnerPile) {
			ArrayList<Card> winnerCards = winnerPile.getDeck();
			playerDeck.addCards(winnerCards);
	}
	
	/**
	 * Returns the value of the chosen category.
	 * 
	 * @return chosenCat, returns the value of the chosen category
	 * */
	public int getChosenCat() {
	    return chosenCat;
	}

	/**
	 * Sets the value of the chosen category.
	 * 
	 * @param chosenCat, integer value of the category a player has chosen
	 * */
	public void setChosenCat(int chosenCat) {
	    this.chosenCat = chosenCat;
	}
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return name of the player
	 * */
	public String getName(){
	    return this.name;
	}

	public abstract int chooseCategory();
	public abstract void promptUser();

	public void addToDeck(ArrayList<Card> winnerPile) {
		playerDeck.addCards(winnerPile);
		
	}
	
//	public void incrementPlayerWins() {
//		playerWins++;
//	}
	
//	public int getPlayerWins() {
//		return playerWins;
//	}
	
}
