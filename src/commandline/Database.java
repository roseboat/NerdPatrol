package commandline;

import java.sql.*;

/**
 * Class responsible for establishing a connection to PostgreSQL database
 * providing save game and retrieving statistics functionality
 * @author NerdPatrol
 *
 */
public class Database {

	/**Connection session instance variable **/
	private Connection connection = null;

	/**
	 * Constructor with method to establish connection upon instantiation
	 */
	public Database() {
		establishConnection();
	}

	/**
	 * Connects to postresql jdbc student database
	 */
	public void establishConnection() {
		//database name, username and password for connecting to student database
		String databaseName = "m_17_2354535k";
		String username = "m_17_2354535k";
		String password = "2354535k";
		
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + databaseName,
					username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		if (connection != null) {
		} else {
			System.err.println("Failed to make connection");
		}
	}

	
	/**
	 * Closes connection to database
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Saves game statistics to database (for 1 AI player)
	 * @param winner - game winner
	 * @param numberRounds - total number of rounds played during game
	 * @param numberDraws - number of draws
	 * @param winsPlayer - number of rounds won by player
	 * @param winsComputer1 - number of rounds won by computer
	 */
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int winsComputer1) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputer1 + ", null, null, null, '" + winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println("The game information has been saved!");
		} catch (SQLException e) {
			System.err.println("error executing query " + query);
		}
	}

	/**
	 * saves game statistics to database (for 2 AI players)
	 * @param winner - game winner
	 * @param numberRounds - total number of rounds played during game
	 * @param numberDraws - number of draws
	 * @param winsPlayer - number of rounds won by player
	 * @param winsComputer1 - number of rounds won by computer
	 */
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int winsComputer1,
			int winsComputer2) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputer1 + ", " + winsComputer2 + ", null, null, '"
				+ winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println("The game information has been saved!");
		} catch (SQLException e) {
			System.err.println("error executing query " + query);
		}
	}

	/**
	 * saves game statistics to database (for 3 AI players)
	 * @param winner - game winner
	 * @param numberRounds - total number of rounds played during game
	 * @param numberDraws - number of draws
	 * @param winsPlayer - number of rounds won by player
	 * @param winsComputer1 - number of rounds won by computer
	 */
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int winsComputer1,
			int winsComputer2, int winsComputer3) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputer1 + ", " + winsComputer2 + ", " + winsComputer3
				+ ", null, '" + winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println("The game information has been saved!");
		} catch (SQLException e) {
			System.err.println("error executing query " + query);
		}
	}

	/**
	 * saves game statistics to database (for 4 AI players)
	 * @param winner - game winner
	 * @param numberRounds - total number of rounds played during game
	 * @param numberDraws - number of draws
	 * @param winsPlayer - number of rounds won by player
	 * @param winsComputer1 - number of rounds won by computer
	 */
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int winsComputer1,
			int winsComputer2, int winsComputer3, int winsComputer4) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputer1 + ", " + winsComputer2 + ", " + winsComputer3
				+ ", " + winsComputer4 + ", '" + winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println("The game information has been saved!");
		} catch (SQLException e) {
			System.err.println("error executing query " + query);
		}
	}

	
	/**
	 * Helper method that retrieves latest game number from database
	 * useful for auto-increment feature.
	 * @return - int highest (current) game number
	 */
	public int gameNumber() {
		Statement stmt = null;
		int gameNumber = 0;
		String query = "SELECT max (gamestats.gamenumber) AS target FROM toptrumps.gamestats";

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int a = rs.getInt("target");
				gameNumber = a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error executing query " + query);
		}

		return gameNumber;
	}
	
	
	/**
	 * Returns the number of games won by the human user
	 * @return - int number of player won games 
	 */
	public int getPlayerWins() {
		
		Statement stmt = null;
		int result = 0;
		String query = "SELECT COUNT(gamestats.winner) AS target FROM toptrumps.gamestats WHERE winner <> 'Computer 1' and winner <> 'Computer 2' and winner <> 'Computer 3' and winner <> 'Computer 4'";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				result = rs.getInt("target");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return result;
	}
	
	
	/**
	 * Returns the number of games won by a computer player
	 * @return - int number of computer won games
	 */
	public int getComputerWins() {
		
		Statement stmt = null;
		int result = 0;
		String query = "SELECT COUNT(gamestats.winner) AS target FROM toptrumps.gamestats WHERE winner = 'Computer 1' or winner = 'Computer 2' or winner = 'Computer 3' or winner = 'Computer 4'";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				result = rs.getInt("target");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return result;
	}
	
	
	/**
	 * Gets the highest recorded number of rounds played in a single game so far
	 * @return int maximum round count
	 */
	public int getMaxRound() {
		
		Statement stmt = null;
		int result = 0;
		String query = "SELECT max(gamestats.totalrounds) AS target FROM toptrumps.gamestats";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				result = rs.getInt("target");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		return result;
	}
	
	
	/**
	 * Helper function that gets the total number of draws from all games
	 * useful for working out average number of draws per game.
	 * @return - int sum of recorded draws in all games
	 */
		public int getNumberDraws() {
			
			Statement stmt = null;
			int result = 0;
			String query = "SELECT sum(gamestats.numberdraws) AS target FROM toptrumps.gamestats";
			try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					result = rs.getInt("target");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("error executing query " + query);
			}
			return result;
		}
	
	
	/**
	 * Returns the required game statistics for display in the command line version. 
	 * @return a String describing game stats
	 */
	public String getGameStatistics() {
		String stats = gameNumber() + " games have been played.\r\n The player has won " + getPlayerWins()
				+ " times.\r\n The computer has won " + getComputerWins()
				+ " times.\r\n The highest number of rounds in a single game is " + getMaxRound()
				+ ".\r\n The average number of draws per game is " + getNumberDraws() / gameNumber() + ".";
		return stats;
	}
	
	/**
	 * Returns required statistics as an array - used in online mode
	 * @return an array of integers - various statistics
	 */
	public int[] getGameStatisticsOnline() {
		int average = getNumberDraws() / gameNumber();
		int[] stats = {gameNumber(), getPlayerWins(), getComputerWins(), getMaxRound(), average};		
		return stats;
	}
}
