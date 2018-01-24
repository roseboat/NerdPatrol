package commandline;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Human extends Player {

	public Human(String name, Deck playerDeck) {
		super(name, playerDeck);
	}

	public void promptUser() {
		
		StringBuilder humanTurn = new StringBuilder(name + ", please choose your value!\r\n");
		humanTurn.append(topCard.cardToString());
		humanTurn.append(String.format("\r\n%5s%5s%5s%8s%5s\r\n", "(1)", "(2)", "(3)",
				"(4)", "(5)"));
		System.out.println(humanTurn);
	}

	// DANTE HANDLE THE EXCEPTIONS PLEASE
	public int altchooseCategory() {

		// reads in user's input
		// user selects the category from a list
		InputStreamReader readInput = new InputStreamReader(System.in);
		Scanner in = new Scanner(readInput);
		int choice = in.nextInt();

		int index = choice - 1;
		// int [] test = topCard.getAllValues();
		// int chosenValue = test[index];

		return index;

	}

	public int chooseCategory() {
		Scanner sc = new Scanner(System.in);
		int index =0;
		int choice=0;
		
		for (;;) {
			
			try{
				choice = sc.nextInt();
				if (choice > 0 && choice < 6) {
					System.out.println(getName() + " has chosen " + topCard.getSelectedCategory(choice - 1));
					index = choice -1;
					setChosenCat(topCard.getAllValues()[index]);
					return index;
				}
				else {
				System.out.println("Please enter a number between 1 and 5");
				chooseCategory();
				return index;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter a number");
				chooseCategory();
				return index;
			}
		}
		
		
//		do {
//				choice = sc.nextInt();
//				if (choice > 0 && choice < 6) {
//					System.out.println(getName() + " has chosen " + topCard.getSelectedCategory(choice - 1));
//				setChosenCat(topCard.getAllValues()[choice - 1]);
//					allowedChoice = true;
//				}
//				else {
//					System.out.println("Number not in allowed range");
//					break;}
//			catch (InputMismatchException e) {
//				System.out.println("Enter a number not a string");
//				break;
//			}
//		} while (!allowedChoice);
//		index = choice -1;
//		return index;
//		
//	
//		try {
//		choice = sc.nextInt();
//		while (!sc.hasNextInt() || choice < 1 || choice > 5) {
//			System.out.println("Please choose a number between 1 and 5 corresponding to your chosen category");
//		}
//		}
//		catch (InputMismatchException e) {
//			System.out.println("Please choose a number between 1 and 5 corresponding to your chosen category");
//		}
//		
//		if (choice > 0 && choice < 6) 
//		System.out.println(getName() + " has chosen " + topCard.getSelectedCategory(choice - 1));
//		setChosenCat(topCard.getAllValues()[choice - 1]);
//
//		index = choice - 1;
//		return index;
		
	}

		// do {
		// switch (choice){
		// case (1):
		// System.out.println("You have chosen " +
		// topCard.getSelectedCategory(choice - 1));
		// setChosenCat(topCard.getAllValues()[choice - 1]);
		// break;
		// case (2):
		// System.out.println("You have chosen " +
		// topCard.getSelectedCategory(choice - 1));
		// setChosenCat(topCard.getAllValues()[choice - 1]);
		// break;
		// case (3):
		// System.out.println("You have chosen " +
		// topCard.getSelectedCategory(choice - 1));
		// break;
		// case (4):
		// System.out.println("You have chosen " +
		// topCard.getSelectedCategory(choice - 1));
		// break;
		// case (5):
		// System.out.println("You have chosen " +
		// topCard.getSelectedCategory(choice - 1));
		// break;
		// default:
		// System.out.println("The number chosen is not an allowed value. Please
		// repick the category");
		// choice = sc.nextInt();
		// }
		// } while (choice < 1 || choice > 5);
}

