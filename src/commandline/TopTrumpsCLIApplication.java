package commandline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	private static ArrayList<Player> players;

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	    	System.out.println("------------------------");
	    	System.out.println("-------Top Trumps-------");
	    	System.out.println("------------------------");

	    
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile = true; // Command line selection
		}


		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			System.out.println("Type 'play' to start a new game");
			System.out.println("Type 'stats' to view game statistics");
			System.out.println("Type 'quit' to quit");
			Scanner sc = new Scanner(System.in);
			String userInput = sc.nextLine();

			if (userInput.matches("play")) {
				GameManager gm = new GameManager("Human Player", 5);

			} else if (userInput.matches("stats")) {
				Database db = new Database();
				System.out.println(db.getGameStatistics());
				db.closeConnection();
				db = null;
			} else if (userInput.matches("quit")) {
				System.out.println("Exiting...");
				userWantsToQuit = true;
				System.exit(1);// do we need this?
			} 
			else {
				System.out.println("Sorry, input not recognised, please try again...");
			}
		}
	}
}

