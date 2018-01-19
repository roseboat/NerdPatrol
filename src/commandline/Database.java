package commandline;

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Database {

	private Connection connection = null;

	// constructor
	public Database() {
		establishConnection();
	}

	// connect to database
	public void establishConnection() {
		String databaseName = "m_17_2354535k";
		String username = "m_17_2354535k";
		String password = "2354535k";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + databaseName,
					username, password);
			// various exceptions caught and handled
		} catch (SQLException e) {
			System.err.println("Connection failed");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Connection successful");
		} else {
			System.err.println("Failed to make connection");
		}
	}

	// disconnect from database
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection could not be closed - SQL exception");
		}
	}

	
	// overloaded methods for variable number of players, there is probably a better
	// way to do this.....

	// for 1 AI player
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int winsComputer1) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputer1 + ", null, null, null, '" + winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("error executing query " + query);

			JOptionPane.showMessageDialog(null, "Error - check data entered correctly");
		}
	}

	// for 2 AI players
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
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("error executing query " + query);

			JOptionPane.showMessageDialog(null, "Error - check data entered correctly");
		}
	}

	// for 3 AI players
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
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("error executing query " + query);

			JOptionPane.showMessageDialog(null, "Error - check data entered correctly");
		}
	}

	// for 4 AI players
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
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("error executing query " + query);

			JOptionPane.showMessageDialog(null, "Error - check data entered correctly");
		}
	}

	// get last game number for auto increment
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
	
	//method to get number of player wins
	public int getPlayerWins() {
		
		Statement stmt = null;
		int result = 0;
		String query = "SELECT COUNT(gamestats.winner) AS target FROM toptrumps.gamestats WHERE winner = 'player'";
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
	
	//method to get number of computer wins
	public int getComputerWins() {
		
		Statement stmt = null;
		int result = 0;
		String query = "SELECT COUNT(gamestats.winner) AS target FROM toptrumps.gamestats WHERE winner = 'computer1' or winner = 'computer2' or winner = 'computer3' or winner = 'computer4'";
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
	
	//method to get highest number of rounds played in a game
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
	
	//method to get the number of draws overall - for working out average number of draws
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
	
	//method to return a string of the data for command line 
	public String getGameStatistics() {
		String stats = gameNumber() + " games have been played. The player has won "+ getPlayerWins() + " times and "
				+ "the computer has won "+getComputerWins()+" times. The highest number of rounds in a single game is "+ getMaxRound()+". The average number of draws per game is "+ getNumberDraws()/gameNumber() + ".";
		
		return stats;
	}


	// for testing
	public static void main(String args[]) {
		Database x = new Database();
		//x.gameStats("player", 30, 5, 20, 1, 1); // two AI player test
		// x.closeConnection();

		System.out.println(x.getGameStatistics());
	}


}
