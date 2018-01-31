package commandline;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

//	//Is this constructor ever used?
	public Card(String name, int a, int b, int c, int d, int e, String cat1, String cat2, String cat3, String cat4, String cat5) {

		this.name = name;
		this.cardValues = new int[] { a, b, c, d, e };
		this.categories = new String[] {cat1, cat2, cat3, cat4, cat5};
	}

	/**
	 * Card constructor instantiates all the category values in an array and the category names.
	 * 
	 * @param line, String of numbers separated by whitespace representing categories 
	 * @param categories, array of category names
	 * @return nothing
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
	
	
	
	// reads the description line from txt file to get titles
	protected String categoryDescTitles() {
		String line = null;

		try {
			line = Files.readAllLines(Paths.get("StarCitizenDeck.txt")).get(0);
			line = line.substring(12);
			String categoryTitleArray[] = line.split("\\s+");
			line = String.format("%-8s%-9s%-9s%-13s%-8s", categoryTitleArray[0],categoryTitleArray[1],categoryTitleArray[2],
					categoryTitleArray[3],categoryTitleArray[4]); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	/**
	 * Returns the name of the card
	 * 
	 * @return name, String name of card
	 * */
	public String getName() {
		return name;
	}
	
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
	protected int getSelectedValue()	{
		return selectedValue;
	}
	
	/**
	 * Set the value selected by the player
	 * 
	 * @param index, integer corresponding to the index of the location
	 * */
	protected void setSelectedValue(int index) {
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

	public void setCardValues(int[] cardValues) {
		this.cardValues = cardValues;
	}

	public int getNUMBER_OF_CATEGORIES() {
		return NUMBER_OF_CATEGORIES;
	}

	public String[] getCategories () {
		return categories;
	}
	
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
	
}
