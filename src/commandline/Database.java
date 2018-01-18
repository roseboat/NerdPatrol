package commandline;

import java.sql.*;

import javax.swing.JOptionPane;

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

	// method to upload game statistics, with parameters for:
	// how many times the player won and each other computer player won, and ultimate
	// winner
	
	//winner is just a string that needs to be consistently be player, or computer1, computer2 etc. it should probably reference something as a foreign key,
	//but it doesn't at the moment...
	
	// ALSO: variable number of players... don't really want to make 4 separate
	// methods depending on how many computer players are playing>>.....?
	
	
	// test for varying arguments - doesn't work
	public void gameStats(String winner, int numberRounds, int numberDraws, int winsPlayer, int... winsComputers) {
		Statement stmt = null;
		// manual auto-increment for game number
		int gameNumber = gameNumber() + 1;
		String query = "INSERT INTO toptrumps.gamestats VALUES (" + gameNumber + ", " + numberRounds + ", "
				+ numberDraws + ", " + winsPlayer + ", " + winsComputers[0] + ", " + winsComputers[1] + ", "
				+ winsComputers[2] + ", '" + winner + "')";

		try {
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate(query);
			System.out.println(rs);
		} catch (SQLException e) {
			System.err.println("error executing query " + query);

			JOptionPane.showMessageDialog(null, "Error - check data entered correctly");
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
	
	

	// for testing
	public static void main(String args[]) {
		Database x = new Database();
		x.gameStats("player", 30, 5, 20, 1,1); // two AI player test
		// x.closeConnection();
	}

}
