package commandline;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Log {

	private final String logFileName = "toptrumps.log";
	private PrintWriter writeToLog;
	
	public Log() {
	
		try {
			writeToLog = new PrintWriter(logFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 1. Writes the contents of the complete deck once it has been read in and constructed
	// 2. The contents of the complete deck after it has been shuffled 
	
	public void logDeck(Deck deck) {
		
		writeToLog.write("The contents of the deck is as follows:\n");
		ArrayList<Card> deckOfCards = deck.getDeck();
		

		for (int i = 0; i < deckOfCards.size(); i++) {
			String card = deckOfCards.get(i).cardToString();
			writeToLog.write(card);
		}
		dash();
	}
	
	//  3.The contents of the user’s deck and the computer’s deck(s) once they have 
	//  been allocated. Be sure to indicate which the user’s deck is and which the 
	//  computer’s deck(s) is. 
	
	public void userDeck(Deck[] userDecks, Player[] users) {

		for (int i = 0; i < users.length; i++) {

			String name = users[i].toString(); // NO IDEA WHAT TO PUT HERE
			writeToLog.write("User deck: " + name);
			ArrayList<Card> ud = userDecks[i].getDeck();

			for (int j = 0; j < userDecks.length; j++) {

				String userCard = ud.get(j).cardToString();
				writeToLog.write(userCard);
				dash();
			}
		}	
	}
	
	// 4. The contents of the communal pile when cards are added or removed from it
	
	public void comPile(Deck communalDeck) {

		writeToLog.write("The communal pile currently contains the following:\n");

		ArrayList<Card> comDeck = communalDeck.getDeck();
		for (int i = 0; i < comDeck.size(); i++) {

			String communalCard = comDeck.get(i).cardToString();
			writeToLog.write(communalCard);

		}
		dash();
	}
	
	//  5. The contents of the current cards in play (the cards from the top of the 
	//  user’s deck and the computer’s deck(s)) 
	
	public void cardsInPlay(Player[] players) {

		writeToLog.write("The current cards in play are:\n");

		for (int i = 0; i < players.length; i++) {

			String user = players[i].toString(); // will need to replace this method i think
			String usersTop = players[i].topCard.cardToString();
			writeToLog.write("The player " + user + "holds the card: \n");
			writeToLog.write(usersTop);
		}
		dash();
	}
	// 6. The category selected and corresponding values when a user or computer 
	//  selects a category 
	public void categoryChosen(Card c) {
	
		
		
		
	}
	
	// 7. The contents of each deck after a round
	// THE SAME METHOD AS METHOD 3???
	public void postRound() {
		
	
	}
	
	
	// 8. The winner of the game
	public void winner(Player winner) {
		// again I think we will need a new method in player to reveal who each player is
		String win = winner.toString();
		writeToLog.write("The winner is: " +win+" !!!");
		dash();
	}
	
	public void close() {
		writeToLog.close();
	}
	
	public void dash() {
		StringBuilder dash = new StringBuilder();

		for (int i = 0; i < 30; i++) {
			dash.append("-");
		}
		String d = dash.toString();
		writeToLog.write(d);
	}
	
	
	
	
	
}
