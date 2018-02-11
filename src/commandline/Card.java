package commandline;

import java.util.Scanner;

/**
 * Card class models a single card within the deck. It holds the information
 * associated with each category in its instance variables.
 * */

public final class Card implements Comparable<Card> {
	private String name;
	private int[] cardValues;
	private int selectedValue;
	private String[] categories;
	private final int NUMBER_OF_CATEGORIES = 5;

	/**
	 * Card constructor instantiates all the category values in an array and the category names.
	 * 
	 * @param line, String of numbers separated by whitespace representing categories 
	 * @param categories, array of category names
	 * */
	protected Card(String line, String[] categories) {
		Scanner sc = new Scanner(line);
		this.name = sc.next();
		this.categories = categories;
		this.cardValues = new int[NUMBER_OF_CATEGORIES];

		for (int i = 0; i < NUMBER_OF_CATEGORIES; i++) {
			cardValues[i] = sc.nextInt();
		}

		sc.close();
	}

	/**
	 * CompareTo method from comparable interface. Players set their selected value
	 * and compare cards via this method. It is configured this way to sort arrays of
	 * Card objects in descending order.
	 * 
	 * @param other, Card object to be compared against
	 * @return 0, Cards are equal
	 * @return -1, Passed Card is larger
	 * @return 1, This Card is larger
	 * */
	@Override
	public int compareTo(Card other) {
		if (this.selectedValue == other.selectedValue) {
			return 0;
		} else if (selectedValue < other.selectedValue) {
			return -1;
		} else
			return 1;
	}

	/**
	 * Creates a string representation of the card.
	 * 
	 * @return showCard, String representation of the card
	 * */
	
	protected String cardToString() {
		StringBuilder showCard = new StringBuilder();
		showCard.append("--------------------\r\n");
		showCard.append(String.format("\t%10s\r\n", getName()));
		for (int i = 0; i < cardValues.length; i++) {
		showCard.append(String.format("%-10s \t%-10d\r\n", categories[i], cardValues[i]));
		}
		showCard.append("--------------------\r\n");
		return showCard.toString();
	}
	
	/**
	 * Returns the name of the card
	 * @return name, String name of card
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the cards
	 * @param name, String name of card
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *Returns the name of the category at selected index
	 *
	 *@param index, int corresponding to the index at the desired location
	 *@return name of the category at selected index
	 * */
	public String getSelectedCategory (int index)	{
		return categories[index];
	}
	
	/**
	 * Returns the selected value of the card
	 * 
	 * @return value of card selected by player
	 * */
	public int getSelectedValue()	{
		return selectedValue;
	}
	
	/**
	 * Set the value selected by the player
	 * 
	 * @param index, integer corresponding to the index of the location
	 * */
	public void setSelectedValue(int index) {
		this.selectedValue = cardValues[index];
	}
	
	/**
	 * Returns the array of category values
	 * 
	 * @return cardValues, array of values for categories
	 * */
	public int[] getCardValues() {
		return this.cardValues;
	}

	/**
	 * Sets an array of card category values
	 * @param cardValues
	 */
	
	public void setCardValues(int[] cardValues) {
		this.cardValues = cardValues;
	}

	/**
	 * Returns the number of categories on the Top Trumps card
	 * @return NUMER_OF_CATEGORIES
	 */
	
	public int getNUMBER_OF_CATEGORIES() {
		return NUMBER_OF_CATEGORIES;
	}

	/**
	 * Returns the name of the Top Trumps card categories
	 * @return categories[], an array of category names
	 */
	public String[] getCategories () {
		return categories;
	}
	
	/**
	 * Sets the name of the Top Trumps card categories
	 * @param categories[], an array of Strings containing the category names
	 */
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
	
}
