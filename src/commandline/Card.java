package commandline;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Card implements Comparable<Card> {
	private String name;
	// private int value1;
	// private int value2;
	// private int value3;
	// private int value4;
	// private int value5;
	private int[] cardValues = new int[5];
	private int selectedCategory;
	private String[] categories;

	public Card(String name, int a, int b, int c, int d, int e) {

		this.name = name;
		this.cardValues = new int[] { a, b, c, d, e };

	}

	// Additional constructor which can take an a single input line ~D
	public Card(String line, String[] categories) {
		Scanner sc = new Scanner(line);
		this.name = sc.next();
		this.categories = categories;

		for (int i = 0; i < 5; i++) {
			cardValues[i] = sc.nextInt();
		}

		sc.close();
	}

	public int getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(int selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getName() {
		return name;
	}

	public int[] getAllValues() {
		return this.cardValues;

	}

	public int getValue(int index) {
		int x = cardValues[index];
		return x;
	}

	// method to compare all cards of pickedCategory in descending order.
	// pickedCategory will be method from PlayerClass.

	public int compareTo(Card other) {
		if (this.selectedCategory == other.selectedCategory) {
			return 0;
		} else if (selectedCategory < other.selectedCategory) {
			return -1;
		} else
			return 1;
	}

	// method to show current card to user
	public String cardToString() {
		String showCard = getName() + "\r\n" + categoryDescTitles() + "\r\n" + getValue(0) + "\t" + getValue(1) + "\t"
				+ getValue(2) + "\t" + getValue(3) + "\t\t" + getValue(4); // stringformat for better alignment to be
																			// added instead of tabs
		return showCard;
	}

	// reads the description line from txt file to get titles
	public String categoryDescTitles() {
		String line = null;

		try {
			line = Files.readAllLines(Paths.get("StarCitizenDeck.txt")).get(0);
			line = line.substring(12);
			String categoryTitleArray[] = line.split("\\s+");
			line = categoryTitleArray[0] + "\t" + categoryTitleArray[1] + "\t" + categoryTitleArray[2] + "\t"
					+ categoryTitleArray[3] + "\t" + categoryTitleArray[4];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/*
	 * public static void main(String[] args) { Card plz = new Card("dickfarts", 17,
	 * 2, 13, 4, 23); // the parameters should read from the starcitizen.txt
	 * System.out.println(plz.cardToString()); }
	 */
}
