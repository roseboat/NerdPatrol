import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
	private String name;
	private int size;
	private int speed;
	private int range;
	private int firePower;
	private int cargo;

	public Card(String name, int size, int speed, int range, int firePower, int cargo) {
		this.name = name;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firePower = firePower;
		this.cargo = cargo;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public int getfirePower() {
		return firePower;
	}

	public int getCargo() {
		return cargo;
	}

	// method to compare all cards of pickedCategory in descending order.
	// pickedCategory will be method from PlayerClass.

/*	public int compareTo(card other) { 
	  if (this.pickedCategory() == other.pickedCategory()) { 
	  return 0; 
	  } if else(this.pickedCategory() < other.pickedCategory()) { 
	  return -1; 
	  } else return 1; }*/

	// method to show current card to user
	public String cardToString() {
		String showCard = getName() + "\r\n" + categoryDescTitles() + "\r\n" + getSize() + "\t" + getSpeed() + "\t"
				+ getRange() + "\t" + getfirePower() + "\t\t" + getCargo(); //stringformat for better alignment to be added instead of tabs 
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

	public static void main(String[] args) {
		Card plz = new Card("dickfarts", 17, 2, 13, 4, 23); // the parameters should read from the starcitizen.txt
		System.out.println(plz.cardToString());
	}
}
