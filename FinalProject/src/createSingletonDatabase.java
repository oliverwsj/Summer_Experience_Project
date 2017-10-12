import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Pietro inspired by
 *         http://codethataint.com/blog/using-singleton-class-for-db-connection-java/
 *         The following class ensures the delicate process of creating a
 *         database is restricted to one instance amongst all threads. Thus,
 *         utilizing the Singleton design principle the constructor is
 *         encapsulated and made private.
 */

public class createSingletonDatabase {

	private static final String PORT_NUMBER = "8889";
	private volatile static createSingletonDatabase singleDBConnection = null;

	/**
	 * Private constructor for the purpose of a singleton implementation.
	 */
	private createSingletonDatabase() {
		Connection conn = createDatabase();
		createTable();
		String filename = "Summer expereince survey 2016 for oliver.csv";
		importData(conn, filename);
	}

	/**
	 * 
	 * @return createSingletonDatabase class, returns a unique instance of the
	 *         creation of a databse.
	 * @throws SQLException
	 */
	public static createSingletonDatabase getInstance() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root", "root"); // MySQL
		ResultSet resultSet = conn.getMetaData().getCatalogs();

		// iterate each catalog in the ResultSet
		boolean databaseExists = false;
		while (resultSet.next()) {
			// Get the database name, which is at position 1
			String databaseName = resultSet.getString(1);
			if (databaseName.equals("experiences")) {
				databaseExists = true;
			}
		}
		resultSet.close();

		if (singleDBConnection == null && (!databaseExists)) {
			synchronized (createSingletonDatabase.class) {
				if (singleDBConnection == null) {
					singleDBConnection = new createSingletonDatabase();
				}
			}
		}
		return singleDBConnection;
	}

	/**
	 * createDatabase() is an internal helper method which enables the creation of a
	 * database central to our experience project.
	 */
	private Connection createDatabase() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", "root",
						"root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();) {
			// Step 3 - create our database
			String sql = "CREATE DATABASE IF NOT EXISTS experiences";
			stmt.execute(sql);
			return conn;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a table in our database, adds the title "question x" to each
	 * 
	 * @return connection which is used to upload and create the database on
	 *         myPHPadmin
	 */

	private Connection createTable() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/experiences?user=root&password=root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();) {
			// Step 3 - create our table
			String sql2 = "CREATE TABLE IF NOT EXISTS t1 ( " + "question1 varchar(500), " + "question2 varchar(500), "
					+ "question3 varchar(500), " + "question4 varchar(500), " + "question5 varchar(500), "
					+ "question6 varchar(500), " + "question7 varchar(500), " + "question8 varchar(500), "
					+ "question9 varchar(500));";
			stmt.execute(sql2);
			return conn;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * imports a csv excel file to the table in our database
	 * 
	 * @param conn
	 *            (the connection to myphpadmin) and filename (the csv excel
	 *            filename) returns void, it is simply a helper method to populate
	 *            the database
	 */
	private void importData(Connection conn, String filename) {

		Statement stmt;
		String query;
		try {
			// Step 1: Allocate a database "Connection" object
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/Experiences?user=root&password=root"); // MySQL
			// Step 2: Allocate a "Statement" object in the Connection
			stmt = conn.createStatement();
			{

			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			query = "LOAD DATA LOCAL INFILE '" + filename
					+ "' INTO TABLE t1  FIELDS TERMINATED BY ',' (question1,question2,question3,question4,question5,question6,question7,question8,question9)";

			stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		}
	}
}