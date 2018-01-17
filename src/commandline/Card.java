package commandline;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Card implements Comparable<Card> {
	private String name;
	private int value1;
	private int value2;
	private int value3;
	private int value4;
	private int value5;
	private final int [] allCardValues = {value1, value2, value3, value4, value5};

	public Card(String name, int a, int b, int c, int d, int e) {
		this.name = name;
		this.value1 = a;
		this.value2 = b;
		this.value3 = c;
		this.value4 = d;
		this.value5 = e;
	}
	
	//Additional constructor which can take an a single input line ~D
	public Card(String line){
	    Scanner sc = new Scanner(line);
	    this.name = sc.next();
	    this.value1 = sc.nextInt();
	    this.value2 = sc.nextInt();
	    this.value3 = sc.nextInt();
	    this.value4 = sc.nextInt();
	    this.value5 = sc.nextInt();
	    sc.close();
	}


	public void setSelectedCategory(int selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getName() {
		return name;
	}

	public int getValue1() {
		return value1;
	}

	public int getValue2() {
		return value2;
	}

	public int getValue3() {
		return value3;
	}

	public int getValue4() {
		return value4;
	}

	public int getValue5() {
		return value5;
	}

	public int [] getAllValues() {
		
	 return this.allCardValues;
		
		
	}
	// method to compare all cards of pickedCategory in descending order.
	// pickedCategory will be method from PlayerClass.

	public int compareTo(Card other) { 
		 if (this.selectedCategory == other.selectedCategory) {
		 return 0;
		 } else if (selectedCategory < other.selectedCategory) {
		 return -1;
		 } else return 1;
	  }

	// method to show current card to user
	public String cardToString() {
		String showCard = getName() + "\r\n" + categoryDescTitles() + "\r\n" + getValue1() + "\t" + getValue2() + "\t"
				+ getValue3() + "\t" + getValue4() + "\t\t" + getValue5(); //stringformat for better alignment to be added instead of tabs 
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

	/*public static void main(String[] args) {
		Card plz = new Card("dickfarts", 17, 2, 13, 4, 23); // the parameters should read from the starcitizen.txt
		System.out.println(plz.cardToString());
	}*/
}
